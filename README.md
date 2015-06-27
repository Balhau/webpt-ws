#Introduction

This is a web-service that lets you search for public data of portuguese (but not only) institutions. This is essentialy a front end
for a library that implements a scrapper for a bunch of public services that render the data not in web services but in traditional web html pages. By doing this the content is not readily for consumption and leaves the programmers without a way to use this data in their projects.

#Web Services

In this documentation we will show how to use the web-services to retrieve data by creating a tutorial document for each of the providers
included in the project

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
