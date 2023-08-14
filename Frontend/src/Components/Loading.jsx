import React from "react";
export default function Loading() {
  return (
    <div className="fixed top-0 right-0 h-screen w-screen z-50 flex justify-center items-center ">
      <span className="loading loading-ring w-60"></span> 
    </div>
  );
}
