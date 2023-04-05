package bu.rhsu.referme

data class Provider(val ID: Int,val name: String,val specialty: List<String>,val phone:String?,
                    val email: String?) {
}