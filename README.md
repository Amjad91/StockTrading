# StockTrading App
stock mobile app, which uses open APIs that provided by Yahoo-Finance for a fast transmit of the stock data. Also, Quandl API for fetch the historical data of stock and used open source library MpAndroid to carry out chart that offer stock analysis.

___

![alt text](https://github.com/Amjad91/StockTrading/blob/master/img/1.png)
![alt text](https://github.com/Amjad91/StockTrading/blob/master/img/2.png)
![alt text](https://github.com/Amjad91/StockTrading/blob/master/img/3.png)
![alt text](https://github.com/Amjad91/StockTrading/blob/master/img/4.png)
![alt text](https://github.com/Amjad91/StockTrading/blob/master/img/5.png)
![alt text](https://github.com/Amjad91/StockTrading/blob/master/img/6.png)
![alt text](https://github.com/Amjad91/StockTrading/blob/master/img/7.png)
![alt text](https://github.com/Amjad91/StockTrading/blob/master/img/8.png)


## Installation
 >add to Gradle/build.gradle (app)
 
 ```
     implementation "androidx.recyclerview:recyclerview-selection:1.1.0-rc01" //RecyclerView to show list of item
    implementation "androidx.cardview:cardview:1.0.0" //CardView to show item in recyclerView
    implementation 'com.squareup.okhttp3:okhttp:3.14.9' //Okttp3 to fetch api data
    implementation 'com.squareup.retrofit2:retrofit:2.9.0' //Retrofit2 to fetch Api data 
    implementation 'com.google.code.gson:gson:2.8.6'  //Gson to read Json file
    implementation 'com.squareup.retrofit2:converter-gson:2.6.0'  //to convert json file 
    implementation 'com.squareup.okhttp3:logging-interceptor:3.6.0' //okhttp3 interceptor
    implementation 'com.github.PhilJay:MPAndroidChart:v3.1.0' //MPAndroidChart library to build Line Chart
    //noinspection GradleCompatible
    implementation 'com.android.support:design:28.0.0'
    implementation 'com.squareup.picasso:picasso:2.5.2' //to show image from Url
    
 ```
 
 ##APIs 
 
 > used free apis
 1. [Yahoo Finance Api](https://apidojo-yahoo-finance-v1.p.rapidapi.com/) Get real time stock data.
 2. [Yahoo Finance Api](https://apidojo-yahoo-finance-v1.p.rapidapi.com/news) Get News list.
 3. [Quandl Api](https://www.quandl.com/api/v3/datasets/) Get historical data -used for chart
 
___
 
 
 
 
 
