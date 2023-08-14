import React from "react";
import { Modal } from "bootstrap";
import { useParams, useNavigate } from "react-router-dom";

export default function PaymentMethod(){
  const params  = useParams();
  const navigate = useNavigate();

  const buyOffer = (bool) =>{ 
    fetch(
        `http://localhost:8080/restaurant/activate/${params.restaurantId}`,
        {
            method: "PATCH",
            headers : {
                "Content-Type" : "application/json",
            },
            body: bool ,
        }
    )
        .then((response) => {
            console.log(response);
            navigate(`/explore`)
            return response.json();
        })
        .catch((error) => {
            console.error("Error while uploading image:", error);
        });
    
  
}



    
  return (
    <>
    

    <div className="min-h-screen flex justify-center items-start bg-tc1">
        <div className="">
            <div className="text-center font-semibold">
                {/* <h1 className="text-5xl">
                    <span className="text-blue-700 tracking-wide">Flexible </span>
                    <span>Plans</span>
                </h1> */}
                <p className="pt-6 text-xl text-gray-400 font-normal w-full px-8 md:w-full">
                    Choose a plan that works best for you and<br/> your team.
                </p>
            </div>
            <div className="pt-24 flex flex-row">
              
                <div className="w-96 p-8 bg-white text-center rounded-3xl pr-16 shadow-xl">
                    <h1 className="text-black font-semibold text-2xl">Basic</h1>
                    <p className="pt-2 tracking-wide">
                        <span className="text-gray-400 align-top">$ </span>
                        <span className="text-3xl font-semibold">45</span>
                        <span className="text-gray-400 font-medium">/ 6 months</span>
                    </p>
                    <hr className="mt-4 border-1"/>
                    <div className="pt-8">
                        <p className="font-semibold text-gray-400 text-left">
                            <span className="material-icons align-middle">
                                done
                            </span>
                            <span className="pl-2">
                                Get started with <span className="text-black">messaging</span>
                            </span>
                        </p>
                        <p className="font-semibold text-gray-400 text-left pt-5">
                            <span className="material-icons align-middle">
                                done
                            </span>
                            <span className="pl-2">
                                Flexible <span className="text-black">team meetings</span>
                            </span>
                        </p>
                        <p className="font-semibold text-gray-400 text-left pt-5">
                            <span className="material-icons align-middle">
                                done
                            </span>
                            <span className="pl-2">
                                <span className="text-black">5 TB</span> cloud storage
                            </span>
                        </p>

                        <a href="#" className="">
                            <p onClick = {() => buyOffer(true)} className="w-full py-4 bg-blue-600 mt-8 rounded-xl text-white">
                                <span className="font-medium">
                                    Buy
                                </span>
                                <span className="pl-2 material-icons align-middle text-sm">
                                    east
                                </span>
                            </p>
                        </a>
                    </div>
                </div>
               
                <div className="w-80 p-8 bg-gray-900 text-center rounded-3xl text-white border-4 shadow-xl border-white transform scale-125">
                    <h1 className="text-white font-semibold text-2xl">Startup</h1>
                    <p className="pt-2 tracking-wide">
                        <span className="text-gray-400 align-top">$ </span>
                        <span className="text-3xl font-semibold">10</span>
                        <span className="text-gray-400 font-medium">/ month</span>
                    </p>
                    <hr className="mt-4 border-1 border-gray-600"/>
                    <div className="pt-8">
                        <p className="font-semibold text-gray-400 text-left">
                            <span className="material-icons align-middle">
                                done
                            </span>
                            <span className="pl-2">
                                All features in <span className="text-white">Basic</span>
                            </span>
                        </p>
                        <p className="font-semibold text-gray-400 text-left pt-5">
                            <span className="material-icons align-middle">
                                done
                            </span>
                            <span className="pl-2">
                                Flexible <span className="text-white">call scheduling</span>
                            </span>
                        </p>
                        <p className="font-semibold text-gray-400 text-left pt-5">
                            <span className="material-icons align-middle">
                                done
                            </span>
                            <span className="pl-2">
                                <span className="text-white">15 TB</span> cloud storage
                            </span>
                        </p>

                        <a href="#" className="">
                            <p onClick = {() =>buyOffer(true)} className="w-full py-4 bg-blue-600 mt-8 rounded-xl text-white">
                                <span className="font-medium">
                                    Buy
                                </span>
                                <span className="pl-2 material-icons align-middle text-sm">
                                    east
                                </span>
                            </p>
                        </a>
                    </div>
                    <div className="absolute top-4 right-4">
                        <p className="bg-blue-700 font-semibold px-4 py-1 rounded-full uppercase text-xs">Popular</p>
                    </div>
                </div>
             
                <div className="w-96 p-8 bg-white text-center rounded-3xl pl-16 shadow-xl">
                    <h1 className="text-black font-semibold text-2xl">Enterprise</h1>
                    <p className="pt-2 tracking-wide">
                        <span className="text-gray-400 align-top">$ </span>
                        <span className="text-3xl font-semibold">70</span>
                        <span className="text-gray-400 font-medium">/ year</span>
                    </p>
                    <hr className="mt-4 border-1"/>
                    <div className="pt-8">
                        <p className="font-semibold text-gray-400 text-left">
                            <span className="material-icons align-middle">
                                done
                            </span>
                            <span className="pl-2">
                                All features in <span className="text-black">Startup</span>
                            </span>
                        </p>
                        <p className="font-semibold text-gray-400 text-left pt-5">
                            <span className="material-icons align-middle">
                                done
                            </span>
                            <span className="pl-2">
                                Growth <span className="text-black">oriented</span>
                            </span>
                        </p>
                        <p className="font-semibold text-gray-400 text-left pt-5">
                            <span className="material-icons align-middle">
                                done
                            </span>
                            <span className="pl-2">
                                <span className="text-black">Unlimited</span> cloud storage
                            </span>
                        </p>

                        <a href="#" className="">
                            <p onClick = {() => buyOffer(true)} className="w-full py-4 bg-blue-600 mt-8 rounded-xl text-white">
                                <span className="font-medium">
                                    Buy
                                </span>
                                <span className="pl-2 material-icons align-middle text-sm">
                                    east
                                </span>
                            </p>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    </>
  )
}