import React from "react";

export default function RatingComponent(){

    const StarComponent = () =>{
        return(
            <svg
            className="w-4 h-4 text-yellow-300 mr-1"
            aria-hidden="true"
            xmlns="http://www.w3.org/2000/svg"
            fill="currentColor"
            viewBox="0 0 22 20"
        >
            <path d="M20.924 7.625a1.523 1.523 0 0 0-1.238-1.044l-5.051-.734-2.259-4.577a1.534 1.534 0 0 0-2.752 0L7.365 5.847l-5.051.734A1.535 1.535 0 0 0 1.463 9.2l3.656 3.563-.863 5.031a1.532 1.532 0 0 0 2.226 1.616L11 17.033l4.518 2.375a1.534 1.534 0 0 0 2.226-1.617l-.863-5.03L20.537 9.2a1.523 1.523 0 0 0 .387-1.575Z" />
        </svg>
        )
    }

    const PercentageLineComponent = ({starNumber}) =>{
        return(
            <div className="flex items-center mt-4">
                    <a
                        href="#"
                        className="text-sm font-medium text-blue-600 dark:text-blue-500 hover:underline"
                    >
                        {starNumber} star
                    </a>
                    <div className="w-2/4 h-5 mx-4 bg-gray-700 rounded dark:bg-gray-700">
                        <div
                            className="h-5 bg-yellow-300 rounded"
                            style={{ width: "70%" }}
                        ></div>
                    </div>
                    <span className="text-sm font-medium text-gray-500 dark:text-gray-400">
                        70%
                    </span>
                </div>
        )
    }

    let resultOutOf_5_Stars = 4.67;


    return (
        <div>
            <p className="flex justify-center p-3 bg-gradient-to-r from-gray-900 via-gray-800 to-gray-900 text-white font-semibold text-xl rounded-lg shadow-lg mb-0">
                Rating
            </p>

            <div className="flex items-center bg-gradient-to-r from-gray-900 via-gray-800 to-gray-900 p-3 pb-0  ">
                
                {[1,2,3,4,5].map((n, index) => <StarComponent key={index}/>)}

                <p className="flex ml-2 mb-0 text-sm font-medium text-gray-500 dark:text-white ">
                    {resultOutOf_5_Stars} out of 5
                </p>
            </div>

            <div className="bg-gradient-to-r from-gray-900 via-gray-800 to-gray-900 p-3 pt-1 ">
                <p className="p-2 text-sm font-medium text-gray-500 dark:text-gray-400">
                    1,745 global ratings
                </p>
             
                {[5,4,3,2,1].map((n, index) => <PercentageLineComponent starNumber={n} key={index}/>)}
            </div>
        </div>
    );
};