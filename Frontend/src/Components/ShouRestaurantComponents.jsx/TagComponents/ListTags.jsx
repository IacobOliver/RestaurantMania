import React from "react";
import { useState } from "react";
import {useAtom} from "jotai";
import state from "../../AtomStates"
import ChangeTagsModal from "./ChangeTagsModal";

function ListTags() {
  const [tagsModal, setTagsModal] = useAtom(state.tagsModal)
  

  const [tags, setTags] = useState([
    "African Cuisine",
    "Middle Eastern Flavors",
    "European Charm",
    "Asian Fusion",
    "Latin American Vibes",
    "Wild West Experience",
    "Sci-Fi Theme",
    "Jazz Lounge",
    "Comedy Club",
    "Vintage Arcade",
    "Wine Cellar",
    "Cigar Lounge",
    "Tiki Bar",
    "Biker's Paradise",
    "Circus Ambiance",
  ]);

  

  return (
    <div className=" bg-gradient-to-r from-gray-900 via-gray-800 to-gray-900">

      <div className="px-2 flex items-center gap-[0.5rem] flex-wrap justify-center" onClick={() =>setTagsModal(true)}>
        {tags.map((tag, index) => (
          <div className="tag-item bg-gradient-to-r from-yellow-400 via-yellow-500 to-yellow-600 hover:p-2 transition-all" key={index} >
            <span className="text">#{tag}</span>
          </div>
        ))}
      </div>

       <ChangeTagsModal
        show={tagsModal}
        onHide={() =>setTagsModal(false)}
        restaurantTags={tags}
        setRestaurantTags = {setTags}
        />
    </div>
  );
}

export default ListTags;
