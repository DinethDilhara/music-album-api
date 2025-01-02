import React, { useEffect, useState } from "react";

export const AlbumsList = () => {
  const [albums, setAlbums] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch("http://localhost:8080/albums")
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        return response.json();
      })
      .then((data) => {
        setAlbums(data);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching albums:", error);
        setLoading(false);
      });
  }, []);

  return (
    <div id="albums">
      <div className="container">
        <div className="section-title text-center">
          <h2>Album List</h2>
        </div>
        {loading ? (
          <p>Loading albums...</p>
        ) : albums.length > 0 ? (
          <div className="row">
            {albums.map((album) => (
              <div key={album.id} className="col-md-6">
                <div className="album-card">
                  <h3 className="album-title">{album.albumName}</h3>
                  <p>
                    <strong>Artist:</strong> {album.artistName}
                  </p>
                  <p>
                    <strong>Release Date:</strong> {album.releaseDate}
                  </p>
                  <p>
                    <strong>Genre:</strong> {album.genre}
                  </p>
                  <div className="songs">
                    <h4>Songs</h4>
                    <ul>
                      {album.songs.map((song) => (
                        <li key={song.id}>
                          {song.songName} (
                          {Math.floor(song.duration / 60)}:
                          {String(song.duration % 60).padStart(2, "0")} mins)
                        </li>
                      ))}
                    </ul>
                  </div>
                </div>
              </div>
            ))}
          </div>
          
        ) : (
          <p>No albums available.</p>
        )}
      </div>
    </div>
  );
};
