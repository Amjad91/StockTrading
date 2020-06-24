package com.example.stocktrading.ui.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stock {


    private QuoteType quoteType;
    private Price price;
    private SummaryDetail summaryDetail;
    @SerializedName("assetProfile")
    @Expose
    private AssetProfile assetProfile;


    private String shortName;
    private String exchange;
    private String symbol;
    private Float previousClose;
    private Float open;
    private Float price1;
    private String bid;
    private String ask;
    private String sector;
    private Integer fullTimeEmployees;
    private String longBusinessSummary;
    private String city;
    private String country;
    private String website;
    private String averageDailyVolume10Day;
    private String marketCap;
    private String regularMarketChange;
    private String regularMarketVolume;

    public Stock(String shortName, String exchange, String symbol,
                 Float previousClose, Float open, Float price1, String bid,
                 String ask, String sector, Integer fullTimeEmployees,
                 String longBusinessSummary, String city, String country,
                 String website, String averageDailyVolume10Day, String marketCap,
                 String regularMarketChange, String regularMarketVolume) {
        this.shortName = shortName;
        this.exchange = exchange;
        this.symbol = symbol;
        this.previousClose = previousClose;
        this.open = open;
        this.price1 = price1;
        this.bid = bid;
        this.ask = ask;
        this.sector = sector;
        this.fullTimeEmployees = fullTimeEmployees;
        this.longBusinessSummary = longBusinessSummary;
        this.city = city;
        this.country = country;
        this.website = website;
        this.averageDailyVolume10Day = averageDailyVolume10Day;
        this.marketCap = marketCap;
        this.regularMarketChange = regularMarketChange;
        this.regularMarketVolume = regularMarketVolume;
    }

    public Stock(String stock, String desc, String symbol) {
        shortName = stock;
        exchange = desc;
        this.symbol = symbol;
    }

    public Stock(String shortName, String exchange, Float previousClose, Float open, Float price1) {
        this.shortName = shortName;
        this.exchange = exchange;
        this.previousClose = previousClose;
        this.open = open;
        this.price1 = price1;
    }

    public String getSymbol() {
        return symbol;
    }

    public Float getPreviousClose() {
        return previousClose;
    }

    public Float getOpen() {
        return open;
    }

    public Float getPrice1() {
        return price1;
    }

    public QuoteType getQuoteType() {
        return quoteType;
    }

    public Price getPrice() {
        return price;
    }

    public SummaryDetail getSummaryDetail() {
        return summaryDetail;
    }

    public AssetProfile getAssetProfile() {
        return assetProfile;
    }

    public String getAsk() {
        return ask;
    }

    public String getSector() {
        return sector;
    }

    public Integer getFullTimeEmployees() {
        return fullTimeEmployees;
    }

    public String getLongBusinessSummary() {
        return longBusinessSummary;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getWebsite() {
        return website;
    }

    public String getAverageDailyVolume10Day() {
        return averageDailyVolume10Day;
    }

    public String getMarketCap() {
        return marketCap;
    }

    public String getRegularMarketChange() {
        return regularMarketChange;
    }

    public String getRegularMarketVolume() {
        return regularMarketVolume;
    }

    public String getShortName() {
        return shortName;
    }


    public String getExchange() {
        return exchange;
    }

    public String getBid() {
        return bid;
    }

    public class QuoteType {
        private String shortName;
        private String exchange;
        private String symbol;

        public String getShortName() {
            return shortName;
        }

        public String getExchange() {
            return exchange;
        }

        public String getSymbol() {
            return symbol;
        }
    }

    public class Price {
        private String currencySymbol;
        private RegularMarketPrice regularMarketPrice;

        @SerializedName("regularMarketChange")
        @Expose
        private RegularMarketChange regularMarketChange;
        @SerializedName("regularMarketVolume")
        @Expose
        private RegularMarketVolume regularMarketVolume;
        @SerializedName("marketCap")
        @Expose
        private MarketCap marketCap;


        public String getCurrencySymbol() {
            return currencySymbol;
        }

        public RegularMarketPrice getRegularMarketPrice() {
            return regularMarketPrice;
        }

        public RegularMarketChange getRegularMarketChange() {
            return regularMarketChange;
        }


        public RegularMarketVolume getRegularMarketVolume() {
            return regularMarketVolume;
        }


        public MarketCap getMarketCap() {
            return marketCap;
        }


        public class RegularMarketPrice {
            public Float raw;

            public Float getRaw() {
                return raw;
            }
        }


        public class RegularMarketChange {

            @SerializedName("raw")
            @Expose
            private Float raw;
            @SerializedName("fmt")
            @Expose
            private String fmt;

            public Float getRaw() {
                return raw;
            }


            public String getFmt() {
                return fmt;
            }


        }

        public class RegularMarketVolume {

            @SerializedName("raw")
            @Expose
            private Integer raw;
            @SerializedName("fmt")
            @Expose
            private String fmt;
            @SerializedName("longFmt")
            @Expose
            private String longFmt;

            public Integer getRaw() {
                return raw;
            }


            public String getFmt() {
                return fmt;
            }


            public String getLongFmt() {
                return longFmt;
            }


        }
    }

    public class SummaryDetail {
        private PreviousClose previousClose;
        private RegularMarketOpen regularMarketOpen;
        @SerializedName("averageDailyVolume10Day")
        @Expose
        private AverageDailyVolume10Day averageDailyVolume10Day;
        @SerializedName("ask")
        @Expose
        private Ask ask;
        @SerializedName("bid")
        @Expose
        private Bid bid;

        public class PreviousClose {
            public Float raw;

            public Float getRaw() {
                return raw;
            }
        }

        public class RegularMarketOpen {
            public Float raw;

            public Float getRaw() {
                return raw;
            }
        }

        public PreviousClose getPreviousClose() {
            return previousClose;
        }

        public RegularMarketOpen getRegularMarketOpen() {
            return regularMarketOpen;
        }

        public AverageDailyVolume10Day getAverageDailyVolume10Day() {
            return averageDailyVolume10Day;
        }


        public Ask getAsk() {
            return ask;
        }


        public Bid getBid() {
            return bid;
        }


    }

    public class Ask {

        @SerializedName("raw")
        @Expose
        private Float raw;
        @SerializedName("fmt")
        @Expose
        private String fmt;

        public Float getRaw() {
            return raw;
        }


        public String getFmt() {
            return fmt;
        }


    }


    public class AssetProfile {

        @SerializedName("sector")
        @Expose
        private String sector;
        @SerializedName("fullTimeEmployees")
        @Expose
        private Integer fullTimeEmployees;
        @SerializedName("longBusinessSummary")
        @Expose
        private String longBusinessSummary;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("website")
        @Expose
        private String website;

        public String getSector() {
            return sector;
        }


        public Integer getFullTimeEmployees() {
            return fullTimeEmployees;
        }


        public String getLongBusinessSummary() {
            return longBusinessSummary;
        }


        public String getCity() {
            return city;
        }


        public String getCountry() {
            return country;
        }


        public String getWebsite() {
            return website;
        }


    }


    public class AverageDailyVolume10Day {

        @SerializedName("raw")
        @Expose
        private Integer raw;
        @SerializedName("fmt")
        @Expose
        private String fmt;
        @SerializedName("longFmt")
        @Expose
        private String longFmt;

        public Integer getRaw() {
            return raw;
        }


        public String getFmt() {
            return fmt;
        }


        public String getLongFmt() {
            return longFmt;
        }


    }


    public class Bid {

        @SerializedName("raw")
        @Expose
        private Float raw;
        @SerializedName("fmt")
        @Expose
        private String fmt;

        public Float getRaw() {
            return raw;
        }


        public String getFmt() {
            return fmt;
        }


    }

    public class MarketCap {

        @SerializedName("fmt")
        @Expose
        private String fmt;
        @SerializedName("longFmt")
        @Expose
        private String longFmt;

        public String getFmt() {
            return fmt;
        }


        public String getLongFmt() {
            return longFmt;
        }


    }


}
