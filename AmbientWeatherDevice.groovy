metadata {
    definition(name: "Ambient Weather Tile", namespace: "CordMaster", author: "Alden Howard") {
        capability "Sensor"
        capability "TemperatureMeasurement"
        capability "RelativeHumidityMeasurement"
        capability "IlluminanceMeasurement"
        capability "PressureMeasurement"
        capability "UltravioletIndex"
        capability "Refresh"
        
        //command "setTemperature", ["number"]
        //command "setHumidity", ["number"]

        //
        // Simplex Technology
        //
        // Currently no metric conversions have been added.
        //
        attribute "absBarometricPressure", "number"
        attribute "relBarometricPressure", "number"
        attribute "dailyRain", "number"
        //attribute "setDate", "string"
        attribute "dewPoint", "number"
        attribute "feelsLike", "number"
        attribute "hourlyRain", "number"
        attribute "humidity", "number"
        attribute "humidityIn", "number"
        //attribute "Last Rain", "string"
        attribute "maxDailyGust", "number"
        attribute "monthlyRain", "number"
        attribute "solarRadiation", "number"
        attribute "temperature", "number"
        attribute "temperatureIn", "number"
        attribute "totalRain", "number"
        attribute "uvIndex", "number"
        attribute "weeklyRain", "number"
        attribute "windDirection", "number" //Conversion from number direction to cardinal needs to be added
        attribute "windGustMPH", "number"
        attribute "windSpeedMPH", "number"    
    }
    
    //needed?
    tiles {
		
        standardTile("refresh", "device.switch") {
            state("default", label: "Refresh", action: "refresh")
        }
        
        // valueTile("temperature", "device.temperature", width: 2, height: 2) {
        //     state("temperature", label: '${currentValue}', unit: "dF")
        // }
    }
}

def refresh() {
	parent.fetchNewWeather()
}

//
//Simplex Technology
//
def setBaromabsin(value) {
    sendEvent(name: "absBarometricPressure", value: value)
}

def setBaromrelin(value) {
    sendEvent(name: "relBarometricPressure", value: value)
}

def setDailyrainin(value) {
    sendEvent(name: "dailyRain", value: value)
}

// Need to normalize the date
// def setDate(value) {
//     sendEvent(name: "Date", value: value)
// }

def setDewPoint(value) {
    sendEvent(name: "dewPoint", value: value)
}

def setFeelsLike(value) {
    sendEvent(name: "feelsLike", value: value)
}

def setHourlyrainin(value) {
    sendEvent(name: "hourlyRain", value: value)
}

def setHumidityin(value) {
    sendEvent(name: "humidityIn", value: value)
}

def setHumidity(value) {
    sendEvent(name: "humidity", value: value)
}

// Need to normalize the date
// def setLastRain(value) {
//     sendEvent(name: "Last Rain", value: value)
// }

def setMaxdailygust(value) {
    sendEvent(name: "maxDailyGust", value: value)
}

def setMonthlyrainin(value) {
    sendEvent(name: "monthlyRain", value: value)
}

def setSolarradiation(value) {
    sendEvent(name: "solarRadiation", value: value)
}

def setTemperature(value) {
    sendEvent(name: "temperature", value: value, unit: '°F')
}

def setTempinf(value) {
    sendEvent(name: "temperatureIn", value: value, unit: '°F')
}

def setTotalrainin(value) {
    sendEvent(name: "totalRain", value: value)
}

def setUV(value) {
    sendEvent(name: "uvIndex", value: value)
}

def setWeeklyrainin(value) {
    sendEvent(name: "weeklyRain", value: value)
}

def setWinddir(value) {
    sendEvent(name: "windDirection", value: value)
}

def setWindgustmph(value) {
    sendEvent(name: "windGustMPH", value: value)
}

def setWindspeedmph(value) {
    sendEvent(name: "windSpeedMPH", value: value)
}
