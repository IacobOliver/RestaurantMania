import React, { useEffect, useState } from "react";
import InfiniteScroll from 'react-infinite-scroll-component';
import Loading from "../../Loading";

const QUANTITY = 20;

export default function ChangeTag({ tags }) {
    const [allTags, setAllTags] = useState([]);
    const [page, setPage] = useState(0);
    const [hasMore, setHasMore] = useState(true);

    useEffect(() => {
        console.log(page)
        fetch(`http://localhost:8080/restaurantTag/getSomeTags/${page}/${QUANTITY}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${localStorage.getItem("token")}`
            }
        })
            .then(res => res.json())
            .then(data => {
                if(data.length < QUANTITY){
                    setHasMore(false);
                }
                setAllTags([...allTags, ...data]);
                console.log(data)
            })
    }, [page])

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
            <div className="px-2 flex items-center gap-[0.5rem] flex-wrap">
                {tags.map((tag, index) => (
                    <div className="tag-item bg-gradient-to-r from-yellow-400 via-yellow-500 to-yellow-600" key={index}>
                        <span className="text">{tag}</span>
                        <span className="close" onClick={() => removeTag(index)}>&times;</span>
                    </div>
                ))}

            </div>

            <input onKeyDown={handleKeyDown} type="text" className="w-full  text-white bg-gray-800  border-gray-800 focus:border-gray-600 focus:border-1 focus:ring-0 rounded-lg mt-3" placeholder="Search a tag" />


            <InfiniteScroll className="h-10 overflow-scroll"
                dataLength={allTags.length}
                next={() => setPage(page + 1)}
                hasMore={hasMore} // Replace with a condition based on your data source
                loader={<Loading />}
                endMessage={<p>No more data to load.</p>}
            >

                {allTags.map((tag, index) => (
                   <p>{tag.name}</p>
                ))}

            </InfiniteScroll>

        </div>
    )
}