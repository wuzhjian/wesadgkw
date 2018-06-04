package study.core

/**
  * Created by Admin on 2018/4/22.
  */
class SecondSortKey(val first:Int, val second: Int) extends Ordered[SecondSortKey] with Serializable{
  override def compare(that: SecondSortKey): Int = {
    if (this.first - that.first != 0) {
      this.first - that.first
    } else {
      this.second - that.second
    }
  }
}
