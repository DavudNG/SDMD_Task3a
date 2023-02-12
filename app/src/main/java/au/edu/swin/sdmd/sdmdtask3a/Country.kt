package au.edu.swin.sdmd.sdmdtask3a

data class Country(val countryName: String, val countrySub: String, var completedCount: Int,
                   var goldCount: Int, var silverCount: Int, var bronzeCount: Int) {
    fun getTotal(): Int { return goldCount + silverCount +bronzeCount }
}