import React from "react";

export default function ChangeTag({ tags }) {


    function handleKeyDown(e) {
        if (e.key !== "Enter") return;
        const value = e.target.value;
        if (!value.trim()) return;
        setTags([...tags, value]);
        e.target.value = "";
      }
    
      function removeTag(index) {
        setTags(tags.filter((el, i) => i !== index));
      }

    return (
        <div className=" bg-gradient-to-r from-gray-900 via-gray-800 to-gray-900">

            <div className="tags-input-container">
                {tags.map((tag, index) => (
                    <div className="tag-item" key={index}>
                        <span className="text">{tag}</span>
                        <span className="close" onClick={() => removeTag(index)}>&times;</span>
                    </div>
                ))}
                <input onKeyDown={handleKeyDown} type="text" className="tags-input text-black" placeholder="Type somthing" />
            </div>
        </div>
    )
}