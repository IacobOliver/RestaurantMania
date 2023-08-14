import React from "react";
import { useAtom } from "jotai";
import state from "../AtomStates";
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";
import { useState } from "react";
import Loading from "../Loading";

export default function MyRestaurantsPopUp() {
  const [loading, setLoading] = useState(false);
  const [user, setUser] = useAtom(state.user);
  console.log(user);
  const [showMyRestaurants, setshowMyRestaurants] = useAtom(
    state.showMyRestaurants
  );
  const [refreshShowRestaurant, setRefreshShowRestaurant] = useAtom(
    state.refreshShowRestaurant
  );
  const navigate = useNavigate();

  const RestaurantItem = ({ restaurantName, restaurantId }) => {
    return (
      <div
        id={restaurantId}
        onClick={(e) => {
          navigate(`/explore/restaurant/${e.target.id}`);
          console.log(e.target.id);
          setshowMyRestaurants(false);
          setRefreshShowRestaurant(refreshShowRestaurant + 1);
        }}
        className="h-15 p-3 flex justify-between sm-375:flex-col my-1 rounded-xl bg-gray-800 hover:bg-gray-700 transition-delay-200 transition duration-300 ease-in-out"
      >
        <div className="flex items-center">
          <img src="/images/dinner_1378230.png" className="mx-2" />
          <p
            id={restaurantId}
            className="flex items-center mb-0 text-lg font-bold"
          >
            {" "}
            {restaurantName ? restaurantName : "Restaurant Name"}{" "}
          </p>
        </div>

        <p id={restaurantId} className="mr-10 ml-5 flex items-center mb-0">
          Last Updated : 2003.07.21
        </p>
      </div>
    );
  };

  const handleAddRestaurant = () => {
    setLoading(true);
    fetch(`http://localhost:8080/restaurant/addRestaurant/${user.id}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ rating: 0 }),
    })
      .then((res) => res.json())
      .then((restaurant) => {
        console.log(restaurant);
        setUser({
          ...user,
          restaurants: [...user.restaurants, restaurant],
        });
        setLoading(false);
        setshowMyRestaurants(false);
        setRefreshShowRestaurant(refreshShowRestaurant + 1);
        navigate(`/explore/restaurant/${restaurant.id}`);
      });

  };
  console.log(user);
  return (
    <div className="flex flex-col h-full text-tc2">
      <div
        className="flex justify-end text-gray-300"
        onClick={() => setshowMyRestaurants(false)}
      >
        <i class="fa fa-close"></i>
      </div>
      {loading ? <Loading /> : <></>}
      <h2 className="w-full flex justify-center p-2 mb-4">
        ~ My Restaurants ~
      </h2>
      <div className="overflow-y-scroll max-h-4/5 rounded-xl scrollbar-hide text-gray-200">
        {user &&
          user.restaurants.map((rest, index) => (
            <RestaurantItem
              key={index}
              restaurantName={rest.name}
              restaurantId={rest.id}
            />
          ))}
      </div>

      <button
        onClick={handleAddRestaurant}
        className="text-2xl my-2 bg-tc2 hover:bg-tc2_2 text-black rounded-xl transition-delay-200 transition duration-300 ease-in-out"
      >
        +
      </button>
    </div>
  );
}
