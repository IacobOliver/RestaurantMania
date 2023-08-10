import React from "react";
import ExploreHead from "../Components/ExplorePageComponents/ExplorerHead";
import RestaurantsList from "../Components/ExplorePageComponents/RestaurantsList";
import { useAtom } from "jotai";
import state from "../Components/AtomStates";
import ModalMyRestaurants from "../Components/MyRestaurants/ModalMyRestaurants";

export default function ExplorePage() {
  const [user, setUser] = useAtom(state.user);



  return (
    <div className="relative w-full">
      <ExploreHead />

      {/* <ModalMyRestaurants show={true} /> */}

      <RestaurantsList />
    </div>
  );
}
