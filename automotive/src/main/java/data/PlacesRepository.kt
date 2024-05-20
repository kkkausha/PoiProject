package data

import data.model.Place

var placeOne = Place(1, "Uss Midway Museum", 32.713740, -117.175127,
    "The latitude of Uss Midway Museum is 32.713740, and the longitude is -117.175127. " +
            "Uss Midway Museum is located in San Diego, United States with the gps coordinates " +
            "of 32° 42' 49.464'' N and 117° 10' 30.4572'' W. The category of Uss Midway Museum is " +
            "Specialty Museums.",
    12.2, 3.1, 5.0)
var placeTwo = Place(2, "San Diego Zoo", 32.735316, 	-117.149046,
    "The San Diego Zoo is a zoo in Balboa Park, San Diego, " +
            "California, housing over 3,700 animals of more than 650 species " +
            "and subspecies. Its parent organization, San Diego Zoo Global, " +
            "is one of the largest[better source needed] zoological membership " +
            "associations in the world, with more than 250,000 member households " +
            "and 130,000 child memberships, representing more than a half million " +
            "people. The San Diego Zoo was a pioneer in the concept of open-air, " +
            "cageless exhibits that re-create natural animal habitats",
    12.2, 3.1, 5.0)
val placeThree = Place(3, "Balboa Park", 32.734148, -117.144553,
    "The latitude of Balboa Park is 32.734148, " +
            "and the longitude is -117.144553. Balboa Park" +
            " is located in San Diego, United States with the gps coordinates " +
            "of 32° 44' 2.9328'' N and 117° 8' 40.3908'' W. The category of Balboa Park are" +
            " Gardens, Parks, Playgrounds.", 8.4, 4.1, 2.0)
var places = listOf(placeOne, placeTwo, placeThree)

class PlacesRepository {
    fun getPlaces(): List<Place>{
        return places
    }
    fun getPlace(PlaceID : Int): Place? {
        return places.find{it.ID == PlaceID}
    }
}