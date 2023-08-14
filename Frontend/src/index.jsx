import React from "react";
import { createRoot } from "react-dom/client";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Layout from "./Pages/Layout";
import "bootstrap/dist/css/bootstrap.css";
import "mdb-react-ui-kit/dist/css/mdb.min.css";
import "@fortawesome/fontawesome-free/css/all.min.css";
import ExplorePage from "./Pages/ExplorePage";
import ShowRestaurant from "./Pages/ShowRestaurant"
import HomePage from "./Pages/HomePage";
import PaymentMethod from "./Components/PaymentMethod/PaymentMethod";


createRoot(document.getElementById("root")).render(
  <BrowserRouter>
  <Layout/>
    <Routes>
      <Route path="/" element={<HomePage/>}></Route>
      <Route path="/explore" element={<ExplorePage />}></Route>
      <Route path="/explore/restaurant/:restaurantId" element={<ShowRestaurant />}></Route>
      <Route path="/createRestaurant" element={<ShowRestaurant/>}/>
      <Route path="/activate/restaurant/:restaurantId" element={<PaymentMethod/>}/>
    </Routes>
    
  </BrowserRouter>
);
