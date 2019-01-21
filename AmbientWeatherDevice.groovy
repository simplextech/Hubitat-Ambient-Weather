metadata {
    definition(name: "Ambient Weather Tile", namespace: "CordMaster", author: "Alden Howard") {
        capability "Sensor"
        capability "Temperature Measurement"
        capability "Relative Humidity Measurement"
        capability "Illuminance Measurement"

        capability "Refresh"
        
        //command "setTemperature", ["number"]
        //command "setHumidity", ["number"]

        //
        // Simplex Technology
        //
        // Currently no metric conversions have been added.
        //
        attribute "Abs Barometric Pressure", "number"
        attribute "Rel Barometric Pressure", "number"
        attribute "Daily Rain", "number"
        //attribute "setDate", "string"
        attribute "Dew Point", "number"
        attribute "Feels Like", "number"
        attribute "Hourly Rain", "number"
        attribute "Humidity", "number"
        attribute "HumidityIn", "number"
        //attribute "Last Rain", "string"
        attribute "Max Daily Gust", "number"
        attribute "Monthly Rain", "number"
        attribute "Solar Radiation", "number"
        attribute "Temperature", "number"
        attribute "TemperatureIn", "number"
        attribute "Total Rain", "number"
        attribute "UV Index", "number"
        attribute "Weekly Rain", "number"
        attribute "Wind Direction", "number" //Conversion from number direction to cardinal needs to be added
        attribute "Wind Gust MPH", "number"
        attribute "Wind Speed MPH", "number"    
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
    sendEvent(name: "Abs Barometric Pressure", value: value)
}

def setBaromrelin(value) {
    sendEvent(name: "Rel Barometric Pressure", value: value)
}

def setDailyrainin(value) {
    sendEvent(name: "Daily Rain", value: value)
}

// Need to normalize the date
// def setDate(value) {
//     sendEvent(name: "Date", value: value)
// }

def setDewPoint(value) {
    sendEvent(name: "Dew Point", value: value)
}

def setFeelsLike(value) {
    sendEvent(name: "Feels Like", value: value)
}

def setHourlyrainin(value) {
    sendEvent(name: "Hourly Rain", value: value)
}

def setHumidityin(value) {
    sendEvent(name: "HumidityIn", value: value)
}

def setHumidity(value) {
    sendEvent(name: "Humidity", value: value)
}

// Need to normalize the date
// def setLastRain(value) {
//     sendEvent(name: "Last Rain", value: value)
// }

def setMaxdailygust(value) {
    sendEvent(name: "Max Daily Gust", value: value)
}

def setMonthlyrainin(value) {
    sendEvent(name: "Monthly Rain", value: value)
}

def setSolarradiation(value) {
    sendEvent(name: "Solar Radiation", value: value)
}

def setTemperature(value) {
    sendEvent(name: "Temperature", value: value, unit: '°F')
}

def setTempinf(value) {
    sendEvent(name: "TemperatureIn", value: value, unit: '°F')
}

def setTotalrainin(value) {
    sendEvent(name: "Total Rain", value: value)
}

def setUV(value) {
    sendEvent(name: "UV Index", value: value)
}

def setWeeklyrainin(value) {
    sendEvent(name: "Weekly Rain", value: value)
}

def setWinddir(value) {
    sendEvent(name: "Wind Direction", value: value)
}

def setWindgustmph(value) {
    sendEvent(name: "Wind Gust MPH", value: value)
}

def setWindspeedmph(value) {
    sendEvent(name: "Wind Speed MPH", value: value)
}
