import React from "react";
import Footer from "../Components/Footer";
import { useAtom } from "jotai";
import state from "../Components/AtomStates";

export default function HomePage() {


  return (
    <>
      <div className="relative">
        <div className="sticky top-0 flex h-screen items-center justify-cente">
          <img
            src="/images/restaurant3.jpg"
            className="h-full w-full object-cover"
            draggable = "false"
          />



          <div className="absolute left-0 right-0 m-auto flex w-2/4 flex-col items-start justify-center gap-4 p-8 sm:p-10 backdrop-blur-lg rounded-lg">
            <h2 className="text-md sm-375:text-2xl font-bold text-white">
              Wanna go out and you don't know where?
            </h2>
            <p className="font-sans text-sm sm-375:text-lg text-white">
              We have all the restaurants around you from wich you can choose
              the perfect place to have a dinner or a drink. Easy, fast, and
              from your home confort.
            </p>
          </div>
          <img
            className="absolute left-0 right-0 m-auto mb-8 flex h-1/10 flex-col  place-self-end gap-4  backdrop-blur-lg rounded-lg"
            src="/images/slide2.gif"
            draggable = "false"
          />
        </div>

        <div className="sticky top-0 flex h-screen items-center justify-center">
          <img
            src="/images/restaurant2.jpg"
            className="h-full w-full object-cover"
            draggable = "false"
          />

          <div className="absolute left-0 right-0 m-auto flex w-2/4 flex-col items-start justify-center gap-4 p-8 backdrop-blur-lg rounded-lg">
            <h2 className="text-md sm-375:text-2xl font-bold text-white">
              Not sure if is what you are looking for?
            </h2>
            <p className="font-sans text-sm sm-375:text-lg text-white">
              Here you can see all their details. You can see their latest menu
              along with food photos. You can see how the restaurant looks like
              and more important, reviews from their previous clients.
            </p>
          </div>
        </div>
        <div className="sticky top-0 flex h-screen items-center justify-center">
          <img
            src="/images/restaurant1.jpg"
            className="h-full w-full object-cover"
          />
          <div className="absolute left-0 right-0 m-auto flex w-2/4 flex-col items-start justify-center gap-4 p-8 backdrop-blur-lg rounded-lg">
            <h2 className="text-md sm-375:text-2xl font-bold text-white">
              You found it and want to make sure you have a table?
            </h2>
            <p className="font-sans text-sm sm-375:text-lg text-white">
              You can make a resevation without any call. You can choose the
              table and you are free to move your resevation how you like.
            </p>
          </div>
        </div>

        {/* <div class="sticky top-0 flex h-screen items-center justify-center">
          <img
            src="/images/restaurant4.jpg"
            class="h-full w-full object-cover"
          />

          <div class="absolute left-0 right-0 m-auto flex w-2/4 flex-col items-start justify-center gap-4 p-10 backdrop-blur-lg rounded-lg">
            <h2 class="text-2xl font-bold text-white">Fourth Section</h2>
            <p class="font-sans text-lg text-white">
              Lorem Ipsum is simply dummy text of the printing and typesetting
              industry. Lorem Ipsum has been the industry's standard dummy text
              ever since the 1500s,
            </p>
          </div>
        </div> */}
      </div>
      <Footer />
    </>
  );
}
