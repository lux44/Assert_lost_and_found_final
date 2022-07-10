package com.lux.assert_lost_and_found

class TaxiResponse (val lostArticleInfo:LostArticleInfo)

class LostArticleInfo (val list_total_count:Int, val row:MutableList<TaxiItem>)
class TaxiItem (val ID:String, val STATUS:String, val REG_DATE:String,val GET_THING:String,val GET_NAME:String,val GET_AREA:String,
val READ_CNT:String, val GET_POSITION:String)