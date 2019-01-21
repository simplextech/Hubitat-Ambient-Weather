definition(name: "Ambient Weather API", namespace: "CordMaster", author: "Alden Howard", description: "A simple api for providing ambient weather access", iconUrl: "", iconX2Url: "")

preferences {
    page(name: "page1", title: "Log In", nextPage: "page2", uninstall: true) {
        section {
            input(name: "applicationKey", title: "Application Key", type: "text", required: true)
            input(name: "apiKey", title: "API Key", type: "text", required: true)
        }
    }
    
    page(name: "page2")
    page(name: "page3")
}

def page2() {
    def stations = []
    def stationMacs = []
    try {
        stations = getStations()
        
        stations.each { stationMacs << it.macAddress }
    } catch(groovyx.net.http.HttpResponseException e) {
        //then unauthorized
        return dynamicPage(name: "page2", title: "Error", nextPage: "page1", uninstall: true) {
            section {
                paragraph("There was an error authorizing you. Please try again.")
            }
        }
    }
    
   	log.debug("Got stations: " + stations)
    
	return dynamicPage(name: "page2", title: "Select Station", nextPage: "page3", uninstall: true) {
		section {
			input(name: "station", title: "Station", type: "enum", options: stationMacs, required: true)
		}
	}
}

def page3() {
    dynamicPage(name: "page3", title: "Confirm Settings", install: true, uninstall: true) {
        section {
            paragraph("Selected station: $station")
        }
        
        section {
            paragraph("Press done to finish")
        }
    }
}

//lifecycle functions
def installed() {
    log.debug("Installed")
    
    addDevice()    
    initialize()    
    runEvery5Minutes(fetchNewWeather)
}

def updated() {
    log.debug("Updated")
    
    unsubscribe()
    initialize() 
}

def initialize() {
    fetchNewWeather()
}

//children
def addDevice() {
    addChildDevice("CordMaster", "Ambient Weather Tile", "AWTILE-$station", null, [completedSetup: true])
}

//fetch functions
def getStations() throws groovyx.net.http.HttpResponseException {
    def data = []
    
    def params = [
        uri: "https://api.ambientweather.net/",
        path: "/v1/devices",
        query: [applicationKey: applicationKey, apiKey: apiKey]
    ]
    
    requestData("/v1/devices", [applicationKey: applicationKey, apiKey: apiKey]) { response ->
        data = response.data
    }
        
    return data
}

def getWeather() throws groovyx.net.http.HttpResponseException {
    def data = []
    
    requestData("/v1/devices/$station", [applicationKey: applicationKey, apiKey: apiKey, limit: 1]) { response ->
        data = response.data
    }
        
	return data[0]
}

def requestData(path, query, code) {
    def params = [
        uri: "https://api.ambientweather.net/",
        path: path,
        query: query
    ]
    
    httpGet(params) { response ->
        code(response)
    }
}

//loop
def fetchNewWeather() {
    log.debug("Getting new weather...")
    
    def weather = getWeather()
    
    log.debug("Weather: " + weather)
    
    // childDevices[0].setTemperature(weather.tempf)
    // childDevices[0].setHumidity(weather.humidity)

    //
    // Simplex Technology
    //
    childDevices[0].setBaromabsin(weather.baromabsin)
    childDevices[0].setBaromrelin(weather.baromrelin)
    childDevices[0].setDailyrainin(weather.dailyrainin)
    // Need to normalize date
    //childDevices[0].setDate(weather.date)    
    childDevices[0].setDewPoint(weather.dewPoint)
    childDevices[0].setFeelsLike(weather.feelsLike)
    childDevices[0].setHourlyrainin(weather.hourlyrainin)
    childDevices[0].setHumidity(weather.humidity)
    childDevices[0].setHumidityin(weather.humidityin)
    // Need to normalize the date
    //childDevices[0].setLastRain(weather.lastRain)    
    childDevices[0].setMaxdailygust(weather.maxdailygust)
    childDevices[0].setMonthlyrainin(weather.monthlyrainin)
    childDevices[0].setSolarradiation(weather.solarradiation)
    childDevices[0].setTemperature(weather.tempf)
    childDevices[0].setTempinf(weather.tempinf)
    childDevices[0].setTotalrainin(weather.totalrainin)
    childDevices[0].setUV(weather.uv)
    childDevices[0].setWeeklyrainin(weather.weeklyrainin)
    childDevices[0].setWinddir(weather.winddir)
    childDevices[0].setWindgustmph(weather.windgustmph)
    childDevices[0].setWindspeedmph(weather.windspeedmph)
}
