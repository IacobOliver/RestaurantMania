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
      <div className="flex justify-center items-center">
        <button
          className="  justify-center font-bold py-2 px-4 rounded inline-flex items-center   text-gray-200 bg-gradient-to-r from-yellow-400 via-yellow-500 to-yellow-600 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-yellow-300 dark:focus:ring-yellow-800 shadow-lg shadow-yellow-500/50 dark:shadow-lg dark:shadow-yellow-800/80  text-sm  text-center"
           onClick={() =>{
            setTagsModal(true);
            console.log(tagsModal)
          }}
        >
          <span>Edit Tags</span>
        </button>
      </div>
      <div className="tags-input-container">
        {tags.map((tag, index) => (
          <div className="tag-item" key={index}>
            <span className="text">{tag}</span>
          </div>
        ))}
      </div>


       <ChangeTagsModal
        show={tagsModal}
        onHide={() =>setTagsModal(false)}
        restaurantTags={tags}
        />
    </div>
  );
}

export default ListTags;
