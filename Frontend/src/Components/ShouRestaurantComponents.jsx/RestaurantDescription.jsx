import React from "react";
import { useRef } from "react";
import { useState } from "react";

export default function RestaurantDescription({
  isHolder,
  thisRestaurant,
  editContentEvent,
}) {
  const [editDescription, setEditDescription] = useState(false);
  const contentRef = useRef(null);
  return (
    <>
      <div className=" flex justify-center p-3 bg-gradient-to-r from-gray-900 via-gray-800 to-gray-900 text-white font-semibold text-xl rounded-lg shadow-lg mb-0">
        <p className={`flex items-center mb-0 text-xl`}>Description</p>

        {editDescription && isHolder ? (
          <i
            onClick={() => {
              editContentEvent(contentRef, "description");
              setEditDescription(false);
            }}
            className="fa fa-check ml-3 mr-3 hover:text-gray-400 flex items-center mt-1"
          >
            {" "}
          </i>
        ) : null}

        {!editDescription && isHolder ?
          <i
            onClick={() => {
              setEditDescription(true);
            }}
            className="fas fa-edit ml-3 mr-3 hover:text-gray-400  flex items-center mt-1"
          ></i> : null}


      </div>
      <p
        ref={contentRef}
        className={`pt-2 pb-3 pr-7 pl-7 bg-gradient-to-r from-gray-900 via-gray-800 to-gray-900 text-white  text-md rounded-lg shadow-lg ${editDescription ? "border border-white" : ""
          }`}
        contentEditable={editDescription}
      >
        {thisRestaurant?.description
          ? thisRestaurant.description
          : "YOUR RESTAURANT DESCRIPTION"}
      </p>
    </>
  );
}
