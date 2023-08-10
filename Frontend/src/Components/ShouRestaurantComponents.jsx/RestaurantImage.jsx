import React from "react";

export default function RestaurantImage({ thisRestaurant, handleImageChange }) {
  return (
    <div className="mt-0">
      <div className="p-3">
        <label htmlFor="fileInput">
          <img
            src={
              thisRestaurant?.image?.imageUrl
                ? thisRestaurant.image.imageUrl
                : "https://icon-library.com/images/add-image-icon-png/add-image-icon-png-15.jpg"
            }
            className="relative h-auto max-h-72 rounded-xl"
          />
        </label>
        <input
          disabled={false}
          type="file"
          id="fileInput"
          className="hidden"
          accept="image/*"
          onChange={(e) => handleImageChange(e)}
        />
      </div>
    </div>
  );
}
