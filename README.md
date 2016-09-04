#Introduction

This is a web-service that lets you search for public data of portuguese (but not only) institutions. This is essentialy a front end
for a library that implements a scrapper for a bunch of public services that render the data not in web services but in traditional web html pages. By doing this the content is not readily for consumption and leaves the programmers without a way to use this data in their projects.

This project is periodically build and available in a [nexus](http://nexus.balhau.net/nexus/) server and
you can find it here

    http://nexus.balhau.net/nexus/index.html#nexus-search;quick~web

If you don't want to mess with nexus, setup a java runtime environment for java 8 you can use the running
[WebPT Demo](http://webpt.balhau.net)


#Web Services

In this documentation we will show how to use the web-services to retrieve data by creating a tutorial document for each of the providers
included in the project.

##Overall description

The webservices are described in the root path of the web application

To access all the services available you just need to do a GET request
to

    http://localhost:8080/

By doing this you'll get a response like the following

    {
      message: [
      {
        className: "org.pt.pub.data.ws.ipma.IpmaController",
        controllerName: "getBeachList",
        arguments: [ ],
        controllPath: "/ws/ipma/forecast/beachlist"
      },
      {
        className: "org.pt.pub.data.ws.ipma.IpmaController",
        controllerName: "getBeachInfo",
        arguments: [
          "arg0,int, idbeach"
        ],
        controllPath: "/ws/ipma/forecast/beachinfo/{idbeach}"
      },
      {
        className: "org.pt.pub.data.ws.ipma.IpmaController",
        controllerName: "getSeismicInfo",
        arguments: [
          "arg0,java.util.Date, fromdate"
        ],
        controllPath: "/ws/ipma/forecast/seismic/{fromdate}"
      },
      {
        className: "org.pt.pub.data.ws.ipma.IpmaController",
        controllerName: "forecastDay",
        arguments: [
          "arg0,int, day"
        ],
        controllPath: "/ws/ipma/forecast/{day}"
      },
      {
        className: "org.pt.pub.data.ws.bdp.BdpController",
        controllerName: "getBdpCategories",
        arguments: [ ],
        controllPath: "/ws/bdp/categories"
      },
      {
        className: "org.pt.pub.data.ws.bdp.BdpController",
        controllerName: "getBdpSeries",
        arguments: [
          "arg0,java.lang.String, categorie"
        ],
        controllPath: "/ws/bdp/category/{categorie}"
      },
      {
        className: "org.pt.pub.data.ws.bdp.BdpController",
        controllerName: "getBdpSerieData",
        arguments: [
          "arg0,java.lang.String, id"
        ],
        controllPath: "/ws/bdp/category/serie/{id}"
      },
      {
        className: "org.pt.pub.data.ws.ine.IneController",
        controllerName: "getAvailableServices",
        arguments: [
          "arg0,int, pagenumber",
          "arg1,int, numperpage"
        ],
        controllPath: "/ws/ine/services/{pagenumber}/{numperpage}"
      },
      {
        className: "org.pt.pub.data.ws.ine.IneController",
        controllerName: "getServiceData",
        arguments: [
          "arg0,java.lang.String, b64url"
        ],
        controllPath: "/ws/ine/service/{b64url}"
      },
      {
        className: "org.pt.pub.data.ws.accuweather.AccuWeatherController",
        controllerName: "getLocationWeather",
        arguments: [
          "arg0,java.lang.String, location"
        ],
        controllPath: "/ws/weather/accu/location/{location}"
      },
      {
        className: "org.pt.pub.data.ws.accuweather.AccuWeatherController",
        controllerName: "getLocations",
        arguments: [
          "arg0,java.lang.String, location"
        ],
        controllPath: "/ws/weather/accu/locations/{location}"
      },
      {
        className: "org.pt.pub.data.ws.RestDescription",
        controllerName: "describeServices",
        arguments: [ ],
        controllPath: "/"
      }
    ],
    error: false,
    statusMessage: "OK"
    }

This uses reflection to list all the spring controllers that
were implemented in a specific package. So as since the
services are implemented with spring rest api and in the
**org.pt.pub.data.ws** package they are automatically
listed by this service.

Now we will give a more detailed description about the services
and example how to use them

##Banco de Portugal

The **Banco de Portugal**:

    According to its Organic Law, Banco de Portugal is a public-law legal person with administrative and financial autonomy and own property.

This institution render valueable information about economical statistics that are not readily available. This project has a set of callable services you can use to fetch valuable data from the statistical database of the institution.

###Categories

By invoking the **http://localhost:8080/ws/bdp/categories** service you can retrieve a JSON/XML response with the following contents

    {
    "message": {
        "rows": [
            {
                "data": [
                    "tvwCategs_0_0_0",
                    "Portugal (Séries: 11)",
                    "826846",
                    "",
                    "6",
                    "5",
                    "0",
                    "0",
                    "0",
                    "0",
                    "1",
                    "0",
                    "",
                    "",
                    "",
                    "false",
                    "0",
                    "2",
                    "1",
                    "1",
                    "0",
                    "",
                    "",
                    "2",
                    "826846",
                    "0",
                    "1"
                ]
            },
            .
            .
            .

By using the **826846** you can call the **http://localhost:8080/ws/bdp/category/826846** and get the following

    {
        "message": {
            "rows": [
                {
                    "data": [
                        "tvwSeries_0_0",
                        "PIB (volume) - t.v.h. - Portugal",
                        "2027390",
                        "",
                        "6",
                        "5",
                        "0",
                        "0",
                        "0",
                        "0",
                        "1",
                        "0",
                        "",
                        "",
                        "",
                        "false",
                        "0",
                        "1",
                        "1",
                        "1",
                        "0",
                        "",
                        "",
                        "1",
                        "2027390",
                        "0",
                        "1"
                    ]
                },
                .
                .
                .

And finally by using the **2027390** you can call the **http://localhost:8080/ws/bdp/category/serie/2027390** to get
a document that will hold the respective statistics

    {
        "message": [
            {
                "rows": [
                    {
                        "data": [
                            "2027390PIB (volume) - t.v.h. - PortugalContas nacionais trimestrais (dados encadeados em volume - ref. 2006; corrigidos da sazonalidade) - Produto interno bruto - taxa de variação homólogaPER",
                            "2027390",
                            "PIB (volume) - t.v.h. - Portugal",
                            "Contas nacionais trimestrais (dados encadeados em volume - ref. 2006; corrigidos da sazonalidade) - Produto interno bruto - taxa de variação homóloga",
                            "",
                            "PER",
                            ""
                        ]
                    }
                ]
            },
            {
                "rows": [
                    {
                        "data": [
                            "31-03-201531-03-2015 0:00:001.5",
                            "31-03-2015",
                            "31-03-2015 0:00:00",
                            "1.5"
                        ]
                    },
                    {
                        "data": [
                            "31-12-201431-12-2014 0:00:000.6",
                            "31-12-2014",
                            "31-12-2014 0:00:00",
                            "0.6"
                        ]
                    },
                    {
                        "data": [
                            "30-09-201430-09-2014 0:00:001.2",
                            "30-09-2014",
                            "30-09-2014 0:00:00",
                            "1.2"
                        ]
                    }
                    .
                    .
                    .

##Instituto Nacional de Estatística (INE)

The INE is the portuguese institute for statistics and gives throught the web page lots of information regarding historical statistical data. Unfortunately, as other public institutions, don't give a structured way to retrieve the data in a more automated/usefull way. So we created (so far) two web services to enable the querying of the INE data.

The first service you can call the

    http://localhost:8080/ws/ine/services/{pagenumber}/{itemsperpage}

This service will return a list of statistics you can query.

For example if you call this

    http://localhost:8080/ws/ine/services/1/25

You'll end with the following response

    {
    "message": {
        "list": [
            {
                "url": "http://www.ine.pt/xportal/xmain?xpid=INE&xpgid=ine_indicadores&indOcorrCod=0001700&contexto=bd&selTab=tab2",
                "desc": "Abstention rate in the elections to European Parliament (%) by Place of residence (NUTS - 2001); Irregular"
            },
            {
                "url": "http://www.ine.pt/xportal/xmain?xpid=INE&xpgid=ine_indicadores&indOcorrCod=0001687&contexto=bd&selTab=tab2",
                "desc": "Abstention rate in the elections to European Parliament (%) by Place of residence (NUTS - 2002); Irregular"
            },
            {
                "url": "http://www.ine.pt/xportal/xmain?xpid=INE&xpgid=ine_indicadores&indOcorrCod=0001699&contexto=bd&selTab=tab2",
                "desc": "Abstention rate in the elections to Municipalities (%) by Place of residence (NUTS - 2001); Irregular"
            },
            {
                "url": "http://www.ine.pt/xportal/xmain?xpid=INE&xpgid=ine_indicadores&indOcorrCod=0001686&contexto=bd&selTab=tab2",
                "desc": "Abstention rate in the elections to Municipalities (%) by Place of residence (NUTS - 2002); Irregular"
            },
            {
                "url": "http://www.ine.pt/xportal/xmain?xpid=INE&xpgid=ine_indicadores&indOcorrCod=0001698&contexto=bd&selTab=tab2",
                "desc": "Abstention rate in the elections to Parliament (%) by Place of residence (NUTS - 2001); Irregular"
            },
            {
                "url": "http://www.ine.pt/xportal/xmain?xpid=INE&xpgid=ine_indicadores&indOcorrCod=0001684&contexto=bd&selTab=tab2",
                "desc": "Abstention rate in the elections to Parliament (%) by Place of residence (NUTS - 2002); Irregular"
            },
            {
                "url": "http://www.ine.pt/xportal/xmain?xpid=INE&xpgid=ine_indicadores&indOcorrCod=0001701&contexto=bd&selTab=tab2",
                "desc": "Abstention rate in the elections to Presidency of Republic (%) by Place of residence (NUTS - 2001); Irregular"
            },
            {
                "url": "http://www.ine.pt/xportal/xmain?xpid=INE&xpgid=ine_indicadores&indOcorrCod=0001685&contexto=bd&selTab=tab2",
                "desc": "Abstention rate in the elections to Presidency of Republic (%) by Place of residence (NUTS - 2002); Irregular"
            },
            {
                "url": "http://www.ine.pt/xportal/xmain?xpid=INE&xpgid=ine_indicadores&indOcorrCod=0006866&contexto=bd&selTab=tab2",
                "desc": "Access to Internet traffic in roaming (MB) by Subscribers origin; Annual"
            },
            {
                "url": "http://www.ine.pt/xportal/xmain?xpid=INE&xpgid=ine_indicadores&indOcorrCod=0006865&contexto=bd&selTab=tab2",
                "desc": "Access to Internet traffic in roaming (Sessions - No.) by Subscribers origin; Annual"
            },
            {
                "url": "http://www.ine.pt/xportal/xmain?xpid=INE&xpgid=ine_indicadores&indOcorrCod=0006893&contexto=bd&selTab=tab2",
                "desc": "Accidents at work (No.) by Cause of accident at work and Type of occurrence that causes injury; Annual"
            },
            {
                "url": "http://www.ine.pt/xportal/xmain?xpid=INE&xpgid=ine_indicadores&indOcorrCod=0006890&contexto=bd&selTab=tab2",
                "desc": "Accidents at work (No.) by Economic activity (Section - CAE Rev. 2.1) and Employment size class; Annual"
            },
            {
                "url": "http://www.ine.pt/xportal/xmain?xpid=INE&xpgid=ine_indicadores&indOcorrCod=0006891&contexto=bd&selTab=tab2",
                "desc": "Accidents at work (No.) by Economic activity (Section - CAE Rev. 3) and Employment size class; Annual"
            },
            {
                "url": "http://www.ine.pt/xportal/xmain?xpid=INE&xpgid=ine_indicadores&indOcorrCod=0006892&contexto=bd&selTab=tab2",
                "desc": "Accidents at work (No.) by Sex and Age group; Annual"
            },
            {
                "url": "http://www.ine.pt/xportal/xmain?xpid=INE&xpgid=ine_indicadores&indOcorrCod=0006894&contexto=bd&selTab=tab2",
                "desc": "Accidents at work (No.) by Type of injury and Injured body part; Annual"
            },
            {
                "url": "http://www.ine.pt/xportal/xmain?xpid=INE&xpgid=ine_indicadores&indOcorrCod=0002701&contexto=bd&selTab=tab2",
                "desc": "Accounting items (Series 2007-2009 - €) of non-governmental organizations for environment by Geographic localization (NUTS - 2002) and Official chart of accounts; Annual"
            },
            {
                "url": "http://www.ine.pt/xportal/xmain?xpid=INE&xpgid=ine_indicadores&indOcorrCod=0002478&contexto=bd&selTab=tab2",
                "desc": "Accounting itens of management operators with drainage and wastewater treatment services (€) by Geographic localization (NUTS - 2002) and Type of accounting item; Annual"
            },
            {
                "url": "http://www.ine.pt/xportal/xmain?xpid=INE&xpgid=ine_indicadores&indOcorrCod=0002475&contexto=bd&selTab=tab2",
                "desc": "Accounting itens of management operators with water suppy services (€) by Geographic localization (NUTS - 2002) and Type of accounting item; Annual"
            },
            {
                "url": "http://www.ine.pt/xportal/xmain?xpid=INE&xpgid=ine_indicadores&indOcorrCod=0007810&contexto=bd&selTab=tab2",
                "desc": "Accredited experts (No.) of EMAS - Eco-management and audit scheme; Annual"
            },
            {
                "url": "http://www.ine.pt/xportal/xmain?xpid=INE&xpgid=ine_indicadores&indOcorrCod=0000439&contexto=bd&selTab=tab2",
                "desc": "Accumulation rate (Early series 2001-2004 - No.) of enterprises with 20 and more persons employed by Economic activity (CAE Rev. 2.1) and Employment size class; Annual"
            },
            {
                "url": "http://www.ine.pt/xportal/xmain?xpid=INE&xpgid=ine_indicadores&indOcorrCod=0000752&contexto=bd&selTab=tab2",
                "desc": "Acidification index (Base 2000 - Mol H+ per t of gas emited) by Activity branch; Annual"
            },
            {
                "url": "http://www.ine.pt/xportal/xmain?xpid=INE&xpgid=ine_indicadores&indOcorrCod=0003319&contexto=bd&selTab=tab2",
                "desc": "Acidification index (Base 2006 - Mol H+ per t of gas emited) by Activity branch; Annual"
            },
            {
                "url": "http://www.ine.pt/xportal/xmain?xpid=INE&xpgid=ine_indicadores&indOcorrCod=0002338&contexto=bd&selTab=tab2",
                "desc": "Acquisitions less disposals of non-financial non-produced assets, at current prices (K.2) (Base 2000 - €) by Institutional sector; Annual"
            },
            {
                "url": "http://www.ine.pt/xportal/xmain?xpid=INE&xpgid=ine_indicadores&indOcorrCod=0000334&contexto=bd&selTab=tab2",
                "desc": "Acquisitions less disposals of valuables chained volume levels (P.53) (Annual growth rate - Base 2000 - %); Quarterly"
            },
            {
                "url": "http://www.ine.pt/xportal/xmain?xpid=INE&xpgid=ine_indicadores&indOcorrCod=0000333&contexto=bd&selTab=tab2",
                "desc": "Acquisitions less disposals of valuables chained volume levels (P.53) (Base 2000 - €); Quarterly"
            }
        ]
    },
    "error": false,
    "statusMessage": "OK"
}

The web service is paginated because the amount of results is big and this way we can fetch the data in a buffered way avoiding as a result heavy computations and large amount of data being transmitted between client and server in only one communication. The *pagenumber* and *itemsperpage* are configurable as we want but is advisable to use a number of *itemsperpage* relatively low to avoid the problems mentioned.


The second webservice you can use is the following

    http://localhost:8080/ws/ine/service/{url64}

This will return the statistical data for one of the entries you got with the previous web service.

If for example you choose the

    {
            "url": "http://www.ine.pt/xportal/xmain?xpid=INE&xpgid=ine_indicadores&indOcorrCod=0001700&contexto=bd&selTab=tab2",
            "desc": "Abstention rate in the elections to European Parliament (%) by Place of residence (NUTS - 2001); Irregular"
        }

Which means that you want to get the statistical data related with *Abstention rate in the Elections to European Parliament* then you must call

    http://localhost:8080/ws/ine/service/aHR0cDovL3d3dy5pbmUucHQveHBvcnRhbC94bWFpbj94cGlkPUlORSZ4cGdpZD1pbmVfaW5kaWNhZG9yZXMmaW5kT2NvcnJDb2Q9MDAwMTY4NyZjb250ZXh0bz1iZCZzZWxUYWI9dGFiMg==

You'll need to convert the *url*

    http://www.ine.pt/xportal/xmain?xpid=INE&xpgid=ine_indicadores&indOcorrCod=0001700&contexto=bd&selTab=tab2

into a base64 representation

    aHR0cDovL3d3dy5pbmUucHQveHBvcnRhbC94bWFpbj94cGlkPUlORSZ4cGdpZD1pbmVfaW5kaWNhZG9yZXMmaW5kT2NvcnJDb2Q9MDAwMTY4NyZjb250ZXh0bz1iZCZzZWxUYWI9dGFiMg==


## Instituto Português do Mar e da Atmosfera [(IPMA)](http://www.ipma.pt/pt/index.html)

This is a public institution that give us a bunch of interesting
information. Here we can get information about the weather
sea conditions, seismic activity among others.

Looking for the service description we see that the first service available
is described as

    {
      className: "org.pt.pub.data.ws.ipma.IpmaController",
      controllerName: "getBeachList",
      arguments: [ ],
      controllPath: "/ws/ipma/forecast/beachlist"
    }


So by calling

    http://localhost:8080/ws/ipma/forecast/beachlist

we get a list of beaches available and the corresponding id.

    {
      idBeach: 203,
      name: "Adraga"
    },
    {
      idBeach: 246,
      name: "Afife"
    },
    ...

This id is usefull to get information about the beach with the service

    {
      className: "org.pt.pub.data.ws.ipma.IpmaController",
      controllerName: "getBeachInfo",
      arguments: [
        "arg0,int, idbeach"
      ],
      controllPath: "/ws/ipma/forecast/beachinfo/{idbeach}"
    },


This  controller will give you information about the beach, for that
you just need to replace *idbeach* with the id of the wanted beach.

For example if you want to know the status of **Afife** beach you just
need to call

    http://localhost:8080/ws/ipma/forecast/beachinfo/246

And you'll get a response like this


    {
      "message": [
      {
        "rows": [
          {
            data: [
              "Hora",
              "Mar total (m)",
              "Ondulação (m)",
              "Direção onda",
              "Periodo onda",
              "Vaga (m)",
              "Vento (nós)",
              "Direção vento",
              "Escala Beaufort",
              "Temp. Água (°C)"
            ]
          },
          {
            "data": [
              "00h",
              "1.1",
              "0.8",
              "",
              "5.7",
              "0.7",
              "9.2",
              "",
              "3",
              "18.5"
            ]
          },
          ...

## The Piratebay

The piratebay is one of the most used torrent trackers. In piratebay you can share the torrents you create with the rest of the community.
The downside of the site is that it is full of spam. To avoid communicate directly with the site it was developed here a scrapper that will query thepiratebay and return a json with all the answers for your query

    Content-type :  application/json
    Method: PUT
    Body: {"query":"Vuelta 2016","page":0,"order":"99"}

the answer for this request is something like this


    {
    "message": [
    {
      "name": "2016 Vuelta a España\r\nStage 8\r\nAugust 27, 2016\r\nNBC Sports Gold\r\n1280x720p\r\n29.97 fps\r\n hr, 36 min, 37 sec",
      "magnetLink": "magnet:?xt=urn:btih:ed2327d0be52ab476d1a06eca28024bcd078854f&dn=2016+Vuelta+a+Espa%26ntilde%3Ba+Stage+8&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969",
      "torrentLink": "https://tpbmirror.us/torrent/15682614/2016_Vuelta_a_Espa_ntilde_a_Stage_8",
      "date": "2016-08-27 22:42:10 GMT",
      "seeders": 16,
      "leechers": 4
    },
    {
      "name": "2016 Vuelta a España\r\nStage 6\r\nAugust 25, 2016\r\nNBC Sports Gold\r\n1280x720p\r\n29.97 fps\r\n3 hr, 17 min, 42 sec",
      "magnetLink": "magnet:?xt=urn:btih:a4ec28520872eb24ba269bb09818928d52d2adc3&dn=2016+Vuelta+a+Espa%26ntilde%3Ba+Stage+6&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969",
      "torrentLink": "https://tpbmirror.us/torrent/15658539/2016_Vuelta_a_Espa_ntilde_a_Stage_6",
      "date": "2016-08-25 23:06:16 GMT",
      "seeders": 14,
      "leechers": 4
    },
    {
      "name": "Torrent from:\r\n~~~~~~~~~~~~ \nhttps://www.torrenting.com\r\n\r\nJoin us and become part of the BIG family! Yarrrrrrrrrrrrrr!!!",
      "magnetLink": "magnet:?xt=urn:btih:e29c553f8e4aeb799ee5e182648bd7c9209b1189&dn=Cycling.la.Vuelta.a.Espana.2016.Stage.4.WEB.x264-OVERTiME&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969",
      "torrentLink": "https://tpbmirror.us/torrent/15637583/Cycling.la.Vuelta.a.Espana.2016.Stage.4.WEB.x264-OVERTiME",
      "date": "2016-08-23 23:41:18 GMT",
      "seeders": 7,
      "leechers": 2
    },
    {
      "name": "2016 Vuelta a España\r\nStage 13\r\nSeptember 2, 2016\r\nNBC Sports Gold\r\n1280x720p\r\n29.97 fps\r\n3 hr, 38 min, 51 sec",
      "magnetLink": "magnet:?xt=urn:btih:94c8f52e97319bf6c7235c4975a979784904d8ff&dn=2016+Vuelta+a+Espa%26ntilde%3Ba+Stage+13&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969",
      "torrentLink": "https://tpbmirror.us/torrent/15737324/2016_Vuelta_a_Espa_ntilde_a_Stage_13",
      "date": "2016-09-02 23:14:36 GMT",
      "seeders": 18,
      "leechers": 18
    },
    {
      "name": "Torrent from:\r\n~~~~~~~~~~~~ \nhttps://www.torrenting.com\r\n\r\nJoin us and become part of the BIG family! Yarrrrrrrrrrrrrr!!!",
      "magnetLink": "magnet:?xt=urn:btih:270c94cbea72f909c2f006e5220bf62207af137f&dn=Cycling.la.Vuelta.a.Espana.2016.Stage.6.WEB.x264-OVERTiME&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969",
      "torrentLink": "https://tpbmirror.us/torrent/15681032/Cycling.la.Vuelta.a.Espana.2016.Stage.6.WEB.x264-OVERTiME",
      "date": "2016-08-27 17:16:58 GMT",
      "seeders": 6,
      "leechers": 2
    },
    {
      "name": "2016 Vuelta a España\r\nStage 5\r\nAugust 24, 2016\r\nNBC Sports Gold\r\n1280x720p\r\n29.97 fps\r\n3 hr, 27 min, 56 sec",
      "magnetLink": "magnet:?xt=urn:btih:5d427720887da6f229521acbdb9018395928d4ff&dn=2016.Vuelta.a.Espa%26ntilde%3Ba.Stage.5.720p.WEB-DL.H264-FINFUNGUS&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969",
      "torrentLink": "https://tpbmirror.us/torrent/15649830/2016.Vuelta.a.Espa_ntilde_a.Stage.5.720p.WEB-DL.H264-FINFUNGUS",
      "date": "2016-08-25 00:31:36 GMT",
      "seeders": 12,
      "leechers": 3
    },
    {
      "name": "2016 Vuelta a España\r\nStage 7\r\nAugust 26, 2016\r\nNBC Sports Gold\r\n1280x720p\r\n29.97 fps\r\n3 hr, 22 min, 19 sec",
      "magnetLink": "magnet:?xt=urn:btih:a3434f70446d08c96c912f301c7621b285a4d4f2&dn=2016+Vuelta+a+Espa%26ntilde%3Ba+Stage+7&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969",
      "torrentLink": "https://tpbmirror.us/torrent/15670589/2016_Vuelta_a_Espa_ntilde_a_Stage_7",
      "date": "2016-08-27 01:07:37 GMT",
      "seeders": 13,
      "leechers": 3
    },
    {
      "name": "2016 Vuelta a España\r\nStage 9\r\nAugust 28, 2016\r\nNBC Sports Gold\r\n1280x720p\r\n29.97 fps\r\n2 hr, 44 min, 38 sec",
      "magnetLink": "magnet:?xt=urn:btih:ecde9abc6c6ecf6cea3c54c824784714a733b82c&dn=2016+Vuelta+a+Espa%26ntilde%3Ba+Stage+9&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969",
      "torrentLink": "https://tpbmirror.us/torrent/15692670/2016_Vuelta_a_Espa_ntilde_a_Stage_9",
      "date": "2016-08-28 21:37:39 GMT",
      "seeders": 14,
      "leechers": 5
    },
    {
      "name": "2016 Vuelta a España\r\nStage 12\r\nSeptember 1, 2016\r\nNBC Sports Gold\r\n1280x720p\r\n29.97 fps\r\n2 hr, 59 min, 44 sec",
      "magnetLink": "magnet:?xt=urn:btih:0b9ede3b09cb0052d92f15c754e9d447ff4eb880&dn=2016+Vuelta+a+Espa%26ntilde%3Ba+Stage+12&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969",
      "torrentLink": "https://tpbmirror.us/torrent/15730014/2016_Vuelta_a_Espa_ntilde_a_Stage_12",
      "date": "2016-09-01 23:26:35 GMT",
      "seeders": 20,
      "leechers": 13
    },
    {
      "name": "2016 Vuelta a España\r\nStage 2\r\nAugust 21, 2016\r\nNBC Sports Gold\r\n1280x720p\r\n29.97 fps\r\n3 hr, 29 min, 5 sec",
      "magnetLink": "magnet:?xt=urn:btih:33b9e3326150d1ae6851d27cd9582b8305adb5de&dn=2016+Vuelta+a+Espa%26ntilde%3Ba+Stage+2&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969",
      "torrentLink": "https://tpbmirror.us/torrent/15617250/2016_Vuelta_a_Espa_ntilde_a_Stage_2",
      "date": "2016-08-22 04:23:08 GMT",
      "seeders": 11,
      "leechers": 2
    },
    {
      "name": "2016 Vuelta a España\r\nStage 4\r\nAugust 23, 2016\r\nNBC Sports Gold\r\n1280x720p\r\n29.97 fps\r\n3 hr, 10 min, 37 sec",
      "magnetLink": "magnet:?xt=urn:btih:571fe3fd45dec609b37100bb769b5c13769f7385&dn=2016+Vuelta+a+Espa%26ntilde%3Ba+Stage+4&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969",
      "torrentLink": "https://tpbmirror.us/torrent/15639082/2016_Vuelta_a_Espa_ntilde_a_Stage_4",
      "date": "2016-08-24 01:48:40 GMT",
      "seeders": 9,
      "leechers": 5
    },
    {
      "name": "2016 Vuelta a España\r\nStage 11\r\nAugust 31, 2016\r\nNBC Sports Gold\r\n1280x720p\r\n29.97 fps\r\n2 hr, 48 min, 53 sec",
      "magnetLink": "magnet:?xt=urn:btih:cfea04890088f9b1ca0d8f0a82200aefd53053a6&dn=2016+Vuelta+a+Espa%26ntilde%3Ba+Stage+11&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969",
      "torrentLink": "https://tpbmirror.us/torrent/15722119/2016_Vuelta_a_Espa_ntilde_a_Stage_11",
      "date": "2016-08-31 22:15:54 GMT",
      "seeders": 22,
      "leechers": 5
    },
    {
      "name": "2016 Vuelta a España\r\nStage 10\r\nAugust 29, 2016\r\nNBC Sports Gold\r\n1280x720p\r\n29.97 fps\r\n2 hr, 56 min, 16 sec",
      "magnetLink": "magnet:?xt=urn:btih:3f0f7947293456e96fa1166616eb928478670f1f&dn=2016+Vuelta+a+Espa%26ntilde%3Ba+Stage+10&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969",
      "torrentLink": "https://tpbmirror.us/torrent/15704481/2016_Vuelta_a_Espa_ntilde_a_Stage_10",
      "date": "2016-08-29 23:26:47 GMT",
      "seeders": 15,
      "leechers": 4
    },
    {
      "name": "2016 Vuelta a España\r\nStage 3\r\nAugust 22, 2016\r\nNBC Sports Gold\r\n1280x720p\r\n29.97 fps\r\n3 hr, 17 min, 27 sec",
      "magnetLink": "magnet:?xt=urn:btih:62bd3d2257470dacd34bf4f3ea47458da0d8303a&dn=2016+Vuelta+a+Espa%26ntilde%3Ba+Stage+3&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969",
      "torrentLink": "https://tpbmirror.us/torrent/15626151/2016_Vuelta_a_Espa_ntilde_a_Stage_3",
      "date": "2016-08-23 00:40:19 GMT",
      "seeders": 14,
      "leechers": 4
    },
    {
      "name": "2016 Vuelta a España\r\nStage 1\r\nAugust 20, 2016\r\nNBC Sports Gold\r\n1280x720p\r\n29.97 fps\r\n2 hr, 44 min, 46 sec",
      "magnetLink": "magnet:?xt=urn:btih:cdf017bf4c7c537d2ec30de37f60e6ff9f0d3f13&dn=2016+Vuelta+a+Espa%26ntilde%3Ba+Stage+1&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969",
      "torrentLink": "https://tpbmirror.us/torrent/15611621/2016_Vuelta_a_Espa_ntilde_a_Stage_1",
      "date": "2016-08-21 14:26:06 GMT",
      "seeders": 6,
      "leechers": 5
    },
    {
      "name": "Torrent from:\r\n~~~~~~~~~~~~ \nhttps://www.torrenting.com\r\n\r\nJoin us and become part of the BIG family! Yarrrrrrrrrrrrrr!!!",
      "magnetLink": "magnet:?xt=urn:btih:73fff56f0ae076343e30abc5e3c6548cb23b3791&dn=Cycling.la.Vuelta.a.Espana.2016.Stage.5.WEB.x264-OVERTiME&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Fzer0day.ch%3A1337&tr=udp%3A%2F%2Fopen.demonii.com%3A1337&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Fexodus.desync.com%3A6969",
      "torrentLink": "https://tpbmirror.us/torrent/15649160/Cycling.la.Vuelta.a.Espana.2016.Stage.5.WEB.x264-OVERTiME",
      "date": "2016-08-24 23:01:30 GMT",
      "seeders": 5,
      "leechers": 2
    }
    ],
    "error": false,
    "statusMessage": "OK"
    }


## Postman Requests

In this [link](https://www.getpostman.com/collections/ea5deb3fe60d5c7bea5c) you can import the Postman requests to try yourself against a running server
