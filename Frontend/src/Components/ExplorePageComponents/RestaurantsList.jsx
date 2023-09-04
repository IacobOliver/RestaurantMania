import React from "react";
import RestauntCard from "./RestaurantCard";
import { useState, useEffect } from "react";

export default function RestaurantsList() {
  const [restaurants, setRestaurants] = useState(null);


  useEffect(() => {
    fetch("http://localhost:8080/restaurant/getAll", {
  method : "GET",
  headers : {
    "Content-Type" : "application/json",
    "Authorization" : "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoZWxsbyIsImlhdCI6MTY5Mzg0MTE1MiwiZXhwIjoyNDM5MTMxMjU5NDMyOTYwfQ.dvDhwFSJd_gfFBNI0FBiUuTkuwqgiemthcc9WaM4NlQ"
  }
    })
      .then((res) => res.json())
      .then((data) => {
        console.log("Restaurants was fetched again!");
        setRestaurants(data);
      });
  }, []);

  // useEffect(() => {
  //   fetch("http://localhost:8080/restaurant/getAll")
  //     .then((res) => res.json())
  //     .then((data) => {
  //       console.log("Restaurants was fetched again!");
  //       setRestaurants(data);
  //     });
  // }, []);


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
