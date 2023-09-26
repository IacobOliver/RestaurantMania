import React from "react";
export default function Loading() {
  return (
    <div className="fixed top-0 right-0 h-screen w-screen z-50 flex justify-center items-center" >
      <div className=" w-56 h-56 rounded-full flex items-center justify-center" style={{backgroundColor:"rgba(0,0,0,0.3)"}}>
      {/* <span className="loading loading-ring w-60"></span>  */}
      <img src="\public\images\loadingPot.gif"/>
      </div>
    </div>
  );
}
