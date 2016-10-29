package com.raritan.chumpi.backend.perihperal_devices;

/* Inspired by
 * javacv/samples/FaceRecognizerInVideo.java:
 * --------------------------------------------------
 * This is an example how to detect face in a video file with javac
 * 
 * @author Vincent He (chinadragon0515@gmail.com)
 * --------------------------------------------------
 * and javacv/samples/OpenCVFaceRecognizer.java:
 * --------------------------------------------------
 * couldn't find any tutorial on how to perform face recognition using OpenCV and Java,
 * so I decided to share a viable solution here. The solution is very inefficient in its
 * current form as the training model is built at each run, however it shows what's needed
 * to make it work.
 *
 * The class below takes two arguments: The path to the directory containing the training
 * faces and the path to the image you want to classify. Not that all images has to be of
 * the same size and that the faces already has to be cropped out of their original images
 * (Take a look here http://fivedots.coe.psu.ac.th/~ad/jg/nui07/index.html if you haven't
 * done the face detection yet).
 *
 * For the simplicity of this post, the class also requires that the training images have
 * filename format: <label>-rest_of_filename.png. For example:
 *
 * 1-jon_doe_1.png
 * 1-jon_doe_2.png
 * 2-jane_doe_1.png
 * 2-jane_doe_2.png
 * ...and so on.
 *
 * Source: http://pcbje.com/2012/12/doing-face-recognition-with-javacv/
 *
 * @author Petter Christian Bjelland
 * 
 * --------------------------------------------------
 *
 * javacv license:
 * --------------------------------------------------
 * You may use this work under the terms of either the Apache License,
 * Version 2.0, or the GNU General Public License (GPL), either version 2,
 * or any later version, with "Classpath" exception (details below).

 * You don't have to do anything special to choose one license or the other
 * and you don't have to notify anyone which license you are using. You are
 * free to use this work in any project (even commercial projects) as long
 * as the copyright header is left intact
 * --------------------------------------------------
 */


import java.io.File;
import java.io.FilenameFilter;
import java.nio.IntBuffer;

import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.opencv_face.FaceRecognizer;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.MatVector;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_face.*;
import static org.bytedeco.javacpp.opencv_highgui.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_objdetect.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;

public class FaceFinder {
    private CascadeClassifier face_cascade;
    private FaceRecognizer faceRecognizer;
    
    public FaceFinder() {
    	face_cascade = new CascadeClassifier("/home/chkr/Projects/hackathon/opencv-2.4.13/data/haarcascades/haarcascade_frontalface_alt.xml");    	
        String trainingDir = "/home/chkr/Projects/hackathon/facedata";

        File root = new File(trainingDir);
        FilenameFilter imgFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                name = name.toLowerCase();
                return name.endsWith(".jpg") || name.endsWith(".pgm") || name.endsWith(".png");
            }
        };
        File[] imageFiles = root.listFiles(imgFilter);
        MatVector images = new MatVector(imageFiles.length);
        Mat labels = new Mat(imageFiles.length, 1, CV_32SC1);
        IntBuffer labelsBuf = labels.createBuffer();
        int counter = 0;
        for (File image : imageFiles) {
            Mat img = imread(image.getAbsolutePath(), CV_LOAD_IMAGE_GRAYSCALE);
                int label = Integer.parseInt(image.getName().split("\\-")[0]);
                images.put(counter, img);
                System.out.println("label:" + label + " name: " + image.getName());
                //imshow("label" + label, img);
                labelsBuf.put(counter, label);
                counter++;
        }

        //faceRecognizer = createFisherFaceRecognizer(0, 4000);
        faceRecognizer = createEigenFaceRecognizer(180, 50000);
        //faceRecognizer = createLBPHFaceRecognizer();
        System.out.println("Number of images: " + images.size());
        faceRecognizer.train(images, labels);
    }
    
    public void startGrabbing() throws InterruptedException {
        OpenCVFrameConverter.ToMat converterToMat = new OpenCVFrameConverter.ToMat();
        OpenCVFrameGrabber grabber = null;
        try {
            grabber = new OpenCVFrameGrabber(0);
            grabber.start();
        } catch (Exception e) {
            System.err.println("Failed start the grabber.");
        }
        Frame videoFrame = null;
        Mat videoMat;
        while (true) {
            try {
				videoFrame = grabber.grab();
			} catch (Exception e) {
				e.printStackTrace();
			}
            videoMat = converterToMat.convert(videoFrame);
            Mat videoMatGray = new Mat();
            cvtColor(videoMat, videoMatGray, COLOR_BGRA2GRAY);
            equalizeHist(videoMatGray, videoMatGray);
            RectVector faces = new RectVector();
            face_cascade.detectMultiScale(videoMatGray, faces);
            for (int i = 0; i < faces.size(); i++) {
                Rect face_i = faces.get(i);
                Mat face = new Mat(videoMatGray, face_i);
                Mat face_resized = new Mat();
                resize(face, face_resized, new Size(1038, 1374), 1.0, 1.0, INTER_CUBIC);
                IntPointer predictionPtr = new IntPointer(1);
                DoublePointer confidencePtr = new DoublePointer(1);
                faceRecognizer.predict(face_resized, predictionPtr, confidencePtr);
                int prediction = predictionPtr.get(0);
                double confidence = confidencePtr.get(0);
                if (prediction == 0) { continue; }
                rectangle(videoMat, face_i, new Scalar(0, 255, 0, 1));
                String box_text = "P: " + prediction + " C: " + confidence;
                int pos_x = Math.max(face_i.tl().x() - 10, 0);
                int pos_y = Math.max(face_i.tl().y() - 10, 0);
                putText(videoMat, box_text, new Point(pos_x, pos_y), FONT_HERSHEY_PLAIN, 1.0, new Scalar(0, 255, 0, 2.0));
                System.out.println("found face with ID: " + prediction);
            }
            // System.out.println("before lp");
            // imshow("livepreview", videoMat);
            waitKey(100);
        }
    }
    public static void main(String[] args) {
    	FaceFinder f = new FaceFinder();
    	try {
			f.startGrabbing();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}
