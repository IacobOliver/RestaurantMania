import React, { useEffect } from "react";
import { Button, Container, Form, Nav, Navbar } from "react-bootstrap";
import { useState, useRef } from "react";
import { useAtom } from "jotai";
import state from "../Components/AtomStates";
import { Link } from "react-router-dom";
import { Navigate } from "react-router-dom";
import ModalForm from "./LoginForm/ModalForm";
import ModalMyRestaurants from "../Components/MyRestaurants/ModalMyRestaurants";
import { useNavigate } from "react-router-dom";
import {checking } from "../Components/Utils";

export default function Layout() {
  const [showMyRestaurants, setShowMyRestaurants] = useAtom(
    state.showMyRestaurants
  );
  const [user, setUser] = useAtom(state.user);
  const [isLoggedIn, setisLoggedIn] = useAtom(state.isLoggedIn);
  const [modalShow, setModalShow] = useAtom(state.modalShow);

  const [sideMenuSmallScreen, setSideMenuSmallScreen] = useState(false);

  const avatarImageRef = useRef(null);
  const accountInfoRef = useRef(null);



  let avatarToggle = true;
  const navigate = useNavigate();
  
  useEffect(()=>{
    checking.autoLogInWithToken({setModalShow,setUser,setisLoggedIn})
  },[])


  const handleLoginClick = () => {
    setModalShow(true);
    // setisLoggedIn(true);
  };

  const handleSignUpClick = () => {
    setModalShow(true);
    // setisLoggedIn(true);
  };

  const handleMyRestaurants = () => {
    setSideMenuSmallScreen(false);
    console.log(isLoggedIn)
    if (isLoggedIn) {
      setShowMyRestaurants(true);
    } else setModalShow(true);
  };

  const SignInAndLoginBtn = () => {
    return (
      <>
        <button
          type="button"
          className="w-full py-3 px-6 text-center rounded-full transition hover:bg-tc1 active:bg-yellow-200 sm:w-max"
          onClick={handleSignUpClick}
        >
          <span className="block text-tc2 font-semibold text-sm">Sign up</span>
        </button>
        <button
          type="button"
          className="w-full py-3 px-6 text-center rounded-full transition hover:bg-tc1 active:bg-yellow-400 sm:w-max"
          onClick={handleLoginClick}
        >
          <span className="block text-tc2 font-semibold text-sm">Login</span>
        </button>
      </>
    );
  };

  const avatarEvent = () => {
    let avatarPositonLeft = avatarImageRef.current.getBoundingClientRect().left;

    if (avatarToggle) {
      accountInfoRef.current.style.left = `${avatarPositonLeft - 60}px`;
      avatarToggle = false;
    } else {
      accountInfoRef.current.style.left = `-1000px`;
      avatarToggle = true;
    }
  };

  const signOutEvent = () => {
    setUser(null);
    setisLoggedIn(false);
    setSideMenuSmallScreen(false);
    accountInfoRef.current.style.left = "-1000px";
    avatarToggle = true;
    localStorage.setItem("token", null)
  };

  const AvatarIcon = () => {
    return (
      <div className="ml-10">
        <img
          ref={avatarImageRef}
          type="button"
          onClick={avatarEvent}
          className="w-10 h-10 rounded-full cursor-pointer"
          src="\images\avatar.webp"
          draggable="false"
        />
        <div
          ref={accountInfoRef}
          className="z-10 bg-gray-900 divide-gray-400 rounded-lg shadow w-44  absolute mt-3"
          style={{ left: "-1000px" }}
        >
          <div className="px-4 py-3 text-sm text-gray-400">
            <div className=" text-lg">
              {user.firstName} {user.lastName}
            </div>
            <div className="font-medium truncate">{user.email}</div>
          </div>

          <ul
            className="py-2 text-sm text-gray-700 dark:text-gray-200"
            aria-labelledby="avatarButton"
          >
            <li>
              {/* <a href="#" className="block px-4 py-2 hover:bg-gray-800 dark:hover:bg-gray-600 dark:hover:text-white">Dashboard</a> */}
              <Link to={`/yourPath`}>
                <button
                  type="button"
                  className="block px-4 py-2 hover:bg-gray-800 dark:hover:bg-gray-600 dark:hover:text-white rounded-xl"
                >
                  DashBoard
                </button>
              </Link>
            </li>
            <li>
              <Link to={`/yourPath`}>
                <button
                  type="button"
                  className="block px-4 py-2 hover:bg-gray-800 dark:hover:bg-gray-600 dark:hover:text-white rounded-xl"
                >
                  Settings
                </button>
              </Link>
            </li>
            <li>
              <Link to={`/yourPath`}>
                <button
                  type="button"
                  className="block px-4 py-2 hover:bg-gray-800 dark:hover:bg-gray-600 dark:hover:text-white rounded-xl"
                >
                  Earnings
                </button>
              </Link>
            </li>
          </ul>
          <div className="py-1">
            {/* <a href="#" className="block px-4 py-2 text-sm text-gray-400 hover:bg-gray-800 dark:hover:bg-gray-600">Sign out</a> */}
            <Link to={`/`}>
              <button
                onClick={signOutEvent}
                type="button"
                className="w-full flex justify-start px-4 py-2 text-sm text-gray-400 hover:bg-gray-800 dark:hover:bg-gray-600"
              >
                Sign Out
              </button>
            </Link>
          </div>
        </div>
      </div>
    );
  };

  const SeparationLine = () => {
    return (
      <div className={`w-full bg-black h-0.5 flex justify-center items-center`}>
        <div className="w-11/12 bg-gray-700 h-0.5 my-1"></div>
      </div>
    );
  };


  const ThreeLinesMenuButtons = () => {
    return (
      <>
        <div
          onClick={() => {
            navigate("/explore");
            setSideMenuSmallScreen(false);
          }}
          className={`flex items-center group hover:bg-gray-900 hover:ml-6 transition duration-300 ease-in-out p-2 rounded-xl ml-3 my-1`}
        >
          <i className="fas fa-compass  text-gray-300 mr-2"></i>
          <button className="text-gray-300 text-xl font-bold group-hover:text-tc2_2">
            Explore
          </button>
        </div>
        <SeparationLine />

        <div
          onClick={handleMyRestaurants}
          className={`flex items-center group hover:bg-gray-900 hover:ml-6 transition duration-300 ease-in-out p-2 rounded-xl ml-3 my-1`}
        >
          <i className="fas fa-hamburger  text-gray-300 mr-2"></i>
          <button className="text-gray-300 text-xl font-bold group-hover:text-tc2_2">
            My Restaurants
          </button>
        </div>
        <SeparationLine />

        <div
          onClick={() => setSideMenuSmallScreen(false)}
          className={`flex items-center group hover:bg-gray-900 hover:ml-6 transition duration-300 ease-in-out p-2 rounded-xl ml-3 my-1`}
        >
          <i className="fa-regular fa-heart  text-gray-300 mr-2"></i>
          <button className="text-gray-300 text-xl font-bold group-hover:text-tc2_2">
            Wish List
          </button>
        </div>
        <SeparationLine />

        <div
          onClick={() => setSideMenuSmallScreen(false)}
          className={`flex items-center group hover:bg-gray-900 hover:ml-6 transition duration-300 ease-in-out p-2 rounded-xl ml-3 my-1`}
        >
          <i className="fas fa-sliders-h text-gray-300 mr-2"></i>
          <button className="text-gray-300 text-xl font-bold group-hover:text-tc2_2">
            Settings
          </button>
        </div>
        <SeparationLine />

        <div
          onClick={() => setSideMenuSmallScreen(false)}
          className={`flex items-center group hover:bg-gray-900 hover:ml-6 transition duration-300 ease-in-out p-2 rounded-xl ml-3 my-1`}
        >
          <i className="fas fa-donate  text-gray-300 mr-2"></i>
          <button className="text-gray-300 text-xl font-bold group-hover:text-tc2_2">
            Earnings
          </button>
        </div>

        {!user && (
          <>
            <SeparationLine />
            <SeparationLine />
            <div className="flex items-center justify-around p-1">
              <div
                onClick={handleSignUpClick}
                className={`flex items-center group hover:bg-gray-900 hover:ml-6 transition duration-300 ease-in-out p-2 rounded-xl ml-3 my-1`}
              >
                <button className="text-gray-300 text-xl font-bold group-hover:text-tc2_2">
                  Sign In
                </button>
              </div>

              <div
                onClick={handleLoginClick}
                className={`flex items-center group hover:bg-gray-900 hover:ml-6 transition duration-300 ease-in-out p-2 rounded-xl ml-3 my-1`}
              >
                <button className="text-gray-300 text-xl font-bold group-hover:text-tc2_2">
                  Log In
                </button>
              </div>
            </div>
          </>
        )}

        {user && (
          <>
            <SeparationLine />
            <SeparationLine />
            <div
              onClick={signOutEvent}
              className={`flex items-center group hover:bg-gray-900 hover:ml-6 transition duration-300 ease-in-out p-2 rounded-xl ml-3 my-1`}
            >
              <i className="fas fa-sign-out-alt  text-gray-300 mr-2"></i>
              <Link to={`/`}>
                <button className="text-gray-300 text-xl font-bold group-hover:text-tc2_2">
                  Sign Out
                </button>
              </Link>
            </div>
          </>
        )}
      </>
    );
  };

  const ThreeLinesMenuConstainer = () => {
    return (
      <div
        className={`absolute top-24 h-auto w-1/2  sm:w-2/5 md:w-4/12 rounded-l-xl bg-black bg-opacity-90 right-0 z-10 animate-fade-left animate-once animate-normal ${
          sideMenuSmallScreen
            ? "animate-fade-left animate-once animate-normal"
            : "animate-fade-right animate-once animate-normal"
        }`}
      >
        {user && (
          <div className="flex items-center justify-center">
            <img
              ref={avatarImageRef}
              type="button"
              className="w-10 h-10 rounded-full cursor-pointer"
              src="\images\avatar.webp"
              draggable="false"
            />
            <div className="py-2 px-1 sm:px-4 sm:py-3  text-sm sm:text-md md:text-lg text-gray-300">
              <div className="font-semibold">
                {user.firstName} {user.lastName}
              </div>
              <div className=" truncate">{user.email}</div>
            </div>
          </div>
        )}

        <ThreeLinesMenuButtons />
      </div>
    );
  };

  const threeLinesMenuEvent = () => {
    setSideMenuSmallScreen(sideMenuSmallScreen ? false : true);
  };

  return (
    <>

      <div className="relative w-full ">
        <nav className="z-10 bg-tc3 w-full  ">
          <div className="container m-auto">
            <div className="flex  justify-between py-3">
              <div className="w-full flex justify-between items-center lg:w-max">
                <div
                  onClick={() => navigate("/")}
                  className="hover:bg-gray-900 p-2 rounded-xl"
                >
                  <img
                    className="w-60"
                    src="../../images/restaurantManiaLogo.png"
                    draggable="false"
                  />
                </div>

                <div className="lg:hidden">
                  <i
                    className="fas fa-bars text-tc2 text-2xl relative -mr-2 "
                    onClick={threeLinesMenuEvent}
                    aria-label="hamburger"
                    id="hamburger"
                  ></i>
                </div>

                {sideMenuSmallScreen == true ? (
                  <ThreeLinesMenuConstainer />
                ) : null}
              </div>

              <div
                onClick={() => navigate("/explore")}
                className="flex items-center group hover:bg-gray-900 p-2 rounded-xl hidden lg:flex"
              >
                <i className="fas fa-compass text-tc2 text-lg mr-2 transition-transform transform rotate-0 group-hover:rotate-180 group-hover:text-xl"></i>
                <button className="text-tc2 text-xl font-bold group-hover:text-tc2_2">
                  Explore
                </button>
              </div>

              <div className="hidden w-full lg:flex flex-wrap justify-end items-center space-y-6 p-6 rounded-xl md:space-y-0 md:p-0 md:flex-nowrap md:bg-transparent lg:w-7/12">
                <div className="text-tc2 lg:pr-4 flex ">
                  <button
                    onClick={handleMyRestaurants}
                    className="block md:px-4 transition hover:text-hc1 mb-0 space-y-6 tracking-wide font-medium text-sm md:space-y-0 hover:bg-gray-900  hover:bg-opacity-70 p-3 rounded-2xl"
                  >
                    My Restaurants
                  </button>
                  <button className="block md:px-4 transition hover:text-hc1 mb-0 space-y-6 tracking-wide font-medium text-sm md:space-y-0  hover:bg-gray-900 hover:bg-opacity-70 p-3 rounded-2xl ">
                    WishList
                  </button>
                </div>
                <div className="w-full space-y-2 border-yellow-200 lg:space-y-0 md:w-max lg:border-l">
                  {user ? <AvatarIcon /> : <SignInAndLoginBtn />}
                </div>
              </div>
            </div>
          </div>
        </nav>
      </div>
      <ModalForm
        show={modalShow}
        onHide={() => setModalShow(false)}
      />

      <ModalMyRestaurants
        show={showMyRestaurants}
        onHide={() => setShowMyRestaurants(false)}
      />
    </>
  );
}
