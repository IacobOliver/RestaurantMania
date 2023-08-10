import React from "react";
export default function Loading() {
  return (
    <div className="fixed top-0 right-0 h-screen w-screen z-50 flex justify-center items-center ">
      <div className="animate-spin rounded-full h-32 w-32 border-t-8 border-b-8 border-gray-900"></div>
    </div>
  );
}
