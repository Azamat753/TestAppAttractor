package com.example.testappattractor.data

class GreetingData {

    fun getGreetingJson(): String {
        return "{" +
                "\"user\" :" +
                "{" +
                "\"first_name\" : \"Azamat\"," +
                "\"photo\" : \"https://avatars.githubusercontent.com/u/54452792?v=4\"," +
                "\"second_name\" : \"Nazarbekov\"," +
                "\"education\": 1," +
                "\"company\": [" +
                "{" +
                "\"name\" : \"Siroca technology\"," +
                "\"position\" : \"Android developer\"" +
                "}," +
                "{" +
                "\"name\" : \"Optima bank\"," +
                "\"position\" : \"Android developer\"" +
                "}" +
                "]" +
                "}" +
                "}"
    }
}