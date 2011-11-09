package models

import com.dd.plist.NSDictionary

class Dwelling( var url: String, var price: Integer) extends BaseEntity
{
  def this() = this("",0)
  
  def toPlist : NSDictionary =  {
    val ret = new NSDictionary

    ret.put("url",url)
    ret.put("id",id)
    ret
  }
}