interface WeatherDetails {
  name: string;
  descr: string;
}

interface WeatherData {
  date: Date;
  temp: number;
  tempMin: number;
  tempMax: number;
  pressure: number;
  humidity: number;
  cloudiness: number;
  windSpeed: number;
  windDirec: number;
  rain: number;
  snow: number
  details: WeatherDetails[];
}

interface Weather {
  city: string;
  forecast: WeatherData[];
}

class WeatherController {
  isRainy: boolean;
  isSnowy: boolean;
  isCloudy: boolean;
  city: string;
  forecast: WeatherData;

  constructor() {
      let today = new Date();
      today.setHours(0,0,0,0);

      $.getJSON('/rest/weather/get', (weather: Weather) => {
        this.city = weather.city;

        let filteredArray = weather.forecast.filter(data => {
          let date = new Date(data.date);
          data.date = date;

          return (date.getDate() == today.getDate() && date.getMonth() == today.getMonth() && date.getFullYear() == today.getFullYear());
        });

        if(filteredArray.length) {
          let sortedArray = filteredArray.sort((a, b) => a.date.getTime() - b.date.getTime());
          this.forecast = sortedArray[0]
          this.isRainy = this.forecast.details.some(detail => { return (detail.descr.toLowerCase().indexOf("rain") >= 0) });
          this.isSnowy = this.forecast.details.some(detail => { return (detail.descr.toLowerCase().indexOf("snow") >= 0) });
          this.isCloudy = this.forecast.cloudiness > 60
        }
      });
  }
}

export default {
    name: 'piWeather',
    config: {
        templateUrl: 'src/weather/pi-weather.html',
        controller: WeatherController
    }
};
