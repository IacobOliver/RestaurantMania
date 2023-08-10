import React from "react";
import { useRef } from "react";
import { useState } from "react";

export default function RestaurantName({thisRestaurant,editContentEvent}) {
  const [editName, setEditName] = useState(false);
  const contentRef = useRef(null);

    

  return (
    <div
      className={`mb-0 pt-4 pb-3  bg-gradient-to-r from-gray-900 via-gray-800 to-gray-900 text-white font-semibold text-2xl rounded-lg shadow-lg flex justify-center `}
    >
      {/* <input className="pt-3 w-full text-center bg-gradient-to-r from-gray-900 via-gray-800 to-gray-900 text-white font-semibold text-xl rounded-lg shadow-lg mb-0 flex justify-center" placeholder={restaurantName}/> */}

      <p
        contentEditable={editName}
        className={`${editName ? "border border-white rounded-lg" : ""}`}
        ref={contentRef}
      >
        {thisRestaurant?.name ? thisRestaurant.name : "RESTAURANT NAME"}
      </p>

      {editName ? (
        <i
          onClick={() => {
            editContentEvent(contentRef, "name");
            setEditName(false)
          }}
          className="fa fa-check ml-3 mr-3 hover:text-gray-400"
        >
          {" "}
        </i>
      ) : (
        <i
          onClick={() => {
           setEditName(true)
          }}
          className="fas fa-edit ml-3 mr-3 hover:text-gray-400"
        ></i>
      )}
    </div>
  );
}
