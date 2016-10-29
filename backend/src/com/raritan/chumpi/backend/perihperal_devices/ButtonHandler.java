package com.raritan.chumpi.backend.perihperal_devices;


import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.util.Console;
import com.pi4j.wiringpi.GpioUtil;
import com.raritan.chumpi.backend.rest.accessors.EventChannel;

public class ButtonHandler {

	
	public ButtonHandler() {
		final Console console = new Console();
		GpioUtil.enableNonPrivilegedAccess();

		// print program title/header
		console.title("<-- The Pi4J Project -->", "GPIO Listen Example");

		final GpioController gpio = GpioFactory.getInstance();
		final GpioPinDigitalInput col1 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_21);
		final GpioPinDigitalInput col2 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_22);

		class MyKey {
			int number;
			boolean status;
			MyKey(int i, boolean s) {
				number = i;
				status = s;
			}
		};
		final MyKey keys[] = new MyKey[14];
		class MyPin {
			Pin pin;
			int key1;
			int key2;
			MyPin(Pin p, int k1, int k2) {
				pin = p; key1 = k1; key2 = k2;
			}
		};
		final MyPin pins[] = {
				new MyPin(RaspiPin.GPIO_26, 0, 7 ),
				new MyPin(RaspiPin.GPIO_23, 1, 8 ),
				new MyPin(RaspiPin.GPIO_27, 2, 9 ),
				new MyPin(RaspiPin.GPIO_24, 3, 10 ),
				new MyPin(RaspiPin.GPIO_28, 4, 11 ),
				new MyPin(RaspiPin.GPIO_29, 5, 12 ),
				new MyPin(RaspiPin.GPIO_25, 6, 13 )
		};
		final GpioPinDigitalInput row[] = new GpioPinDigitalInput[7];
		for (int i = 0; i < 7; i++) {
			row[i] = gpio.provisionDigitalInputPin(pins[i].pin);
		}
		for (int i = 0; i < 14; i++) {
			keys[i] = new MyKey(i + 1, false);
		}
		col1.setShutdownOptions(true);
		col1.setDebounce(15);
		col2.setShutdownOptions(true);
		col2.setDebounce(15);

		GpioPinListenerDigital myColListener = new GpioPinListenerDigital() {
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				if (event.getEdge() == PinEdge.FALLING) { return; }
				for (int i = 0; i < 7; i++) {
					int key;
					if (event.getPin().getPin() == RaspiPin.GPIO_21) {
						key = pins[i].key1;
					} else {
						key = pins[i].key2;
					}

					if (row[i].isHigh()) {
						if (keys[key].status) {
							// already pressed, do nothering
						} else {
							// newly pressed
							keys[key].status = true;
							console.println("key " + keys[key].number + " pressed");
							EventChannel.getInstance().send("keypress:" + keys[key].number);
						}
					} else {
						if (keys[key].status) {
							// key was pressed, but isn't anymore
							keys[key].status = false;
							console.println("key " + keys[key].number + " released");
							EventChannel.getInstance().send("keyrelease:" + keys[key].number);
						} else {
							// not pressed
						}
					}
				}
			}
		};

		col1.addListener(myColListener);
		col2.addListener(myColListener);

		

		// forcefully shutdown all GPIO monitoring threads and scheduled tasks
		//gpio.shutdown();
	}
}
