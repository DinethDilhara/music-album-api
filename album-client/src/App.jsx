import React, { useState, useEffect } from "react";
import { AlbumsList } from "./components/albumlist"; 
import SmoothScroll from "smooth-scroll";
import "./App.css";

export const scroll = new SmoothScroll('a[href*="#"]', {
  speed: 1000,
  speedAsDuration: true,
});

const App = () => {
  const [albumsData, setAlbumsData] = useState([]); 

  useEffect(() => {
    fetch("http://localhost:8080/albums")
      .then((response) => response.json())
      .then((data) => setAlbumsData(data))
      .catch((error) => console.error("Error fetching albums:", error));
  }, []);

  return (
    <div>
      <AlbumsList data={albumsData} /> {/* Pass the albumsData to the AlbumsList component */}
    </div>
  );
};

export default App;
