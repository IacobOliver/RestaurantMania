import React from "react";
import RestauntCard from "./RestaurantCard";
import { useState, useEffect } from "react";

export default function RestaurantsList() {
  const [restaurants, setRestaurants] = useState(null);


  useEffect(() => {
    fetch("http://localhost:8080/restaurant/getAll/withoutMenu", {
  // method : "GET",
  // withCredentials: "true",    
  // crossorigin: "true",   
  headers : {
    // "Authorization" : "Bearer eyJhbGciOiJub25lIn0.eyJzdWIiOiJoZWxsbyIsImlhdCI6MTY5MzkwMzcwMiwiZXhwIjoyNDM5MjIxMzMxOTMyNjQwfQ.",
    "Content-Type" : "application/json",
    // "Access-Control-Allow-Origin" : "*",
    // "Access-Control-Allow-Credentials" : "true",
    // 'Accept': 'application/json',
    // 'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept, Authorization',
    // 'Access-Control-Request-Method': 'GET'
  }
    })
      .then((res) => res.json())
      .then((data) => {
        console.log("Restaurants was fetched again!");
        console.log(data)
        setRestaurants(data);
      });
  }, []);


  return (
    <div className=" z-10 min-h-screen bg-gray-700 flex justify-center items-center py-20">
      <div className="md:px-4 md:grid md:grid-cols-2 lg:grid-cols-3 gap-5 space-y-4 md:space-y-0">
        {restaurants ? (
          restaurants.map((rest, index) =>
            rest.active == true ? (
              <RestauntCard key={index} restaurant={rest} />
            ) : null
          )
        ) : (
          <div className="top-0 right-0 h-screen w-screen flex justify-center items-start ">
            {" "}
            <span className="loading loading-ring w-60  text-tc2"></span>
          </div>
        )}
      </div>
    </div>
  );
}
