import React from "react";
import RestauntCard from "./RestaurantCard";
import {useState, useEffect} from "react"

export default function RestaurantsList() {
    const [restaurants, setRestaurants] = useState(null);

    useEffect(() =>{
        fetch("http://localhost:8080/restaurant/getAll")
        .then(res => res.json())
        .then(data =>{
          console.log("Restaurants was fetched again!");
          setRestaurants(data);
        })
    },[])



  return (
    <div className=" z-10 min-h-screen bg-gray-700 flex justify-center items-center py-20">
      <div className="md:px-4 md:grid md:grid-cols-2 lg:grid-cols-3 gap-5 space-y-4 md:space-y-0">
      
      {restaurants && restaurants.map((rest, index) => <RestauntCard key={index} restaurant={rest}/>)}

      </div>
    </div>
  );
}
