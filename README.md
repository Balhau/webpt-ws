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
