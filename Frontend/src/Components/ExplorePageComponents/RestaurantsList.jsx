import React from "react";
import RestauntCard from "./RestaurantCard";
import { useState, useEffect } from "react";
import InfiniteScroll from 'react-infinite-scroll-component';
import Loading from "../Loading";

const QUANTITY = 6;

export default function RestaurantsList() {
  const [restaurants, setRestaurants] = useState([]);
  const [page, setPage] = useState(0);
  const [hasMore, setHasMore] = useState(true);


  useEffect(() =>{
      fetch(`http://localhost:8080/restaurant/getSome/${page}/${QUANTITY}`)
      .then(res => res.json())
      .then(data => {
        if(data.length < QUANTITY){
          setHasMore(false)
        }
        setRestaurants([...restaurants, ...data] )

      })
  },[page])


  return (
    <div className=" z-10 min-h-screen bg-gray-700 flex justify-center items-center py-20">

        <InfiniteScroll className="md:px-4 md:grid md:grid-cols-2 lg:grid-cols-3 gap-5 space-y-4 md:space-y-0"
      dataLength={restaurants.length}
      next={() => setPage(page + 1)}
      hasMore={hasMore} // Replace with a condition based on your data source
      loader={<Loading/>}
      endMessage={<p>No more data to load.</p>}
    >
      
        {restaurants.map((retaurant, index) => (
          <RestauntCard key={index} restaurant={retaurant} />
        ))}
      
    </InfiniteScroll>


    </div>
  );
}


  // useEffect(() => {
  //   fetch("http://localhost:8080/restaurant/getAll/withoutMenu", {
  // // method : "GET",
  // // withCredentials: "true",    
  // // crossorigin: "true",   
  // headers : {
  //   // "Authorization" : "Bearer eyJhbGciOiJub25lIn0.eyJzdWIiOiJoZWxsbyIsImlhdCI6MTY5MzkwMzcwMiwiZXhwIjoyNDM5MjIxMzMxOTMyNjQwfQ.",
  //   "Content-Type" : "application/json",
  //   // "Access-Control-Allow-Origin" : "*",
  //   // "Access-Control-Allow-Credentials" : "true",
  //   // 'Accept': 'application/json',
  //   // 'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept, Authorization',
  //   // 'Access-Control-Request-Method': 'GET'
  // }
  //   })
  //     .then((res) => res.json())
  //     .then((data) => {
  //       console.log("Restaurants was fetched again!");
  //       console.log(data)
  //       setRestaurants(data);
  //     });
  // }, []);