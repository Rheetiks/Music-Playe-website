
showPlaylist();

function showPlaylist() {
    let url = 'http://localhost:8088/displayPlaylists';
    let playlists = displayGetApi(url).then(function displaydata(val) {
        console.log(val)
        let PlaylistName = document.getElementById('PlaylistName');
        PlaylistName.innerHTML += val.map((playlists) =>
            `
            <button id="playlistbtn" onclick="displayPlaylistSongs(${playlists.playlistId},'${playlists.playlistName}'),removeElem()">${playlists.playlistName}</button>
            `
        ).join("");
    });

}
removeElem();

function removeElem() {
    let choosePlaylist = document.getElementById('PlaylistName');
    let songBox = document.getElementById('songsbox')
    if (songBox.style.display == "") {
        choosePlaylist.style.display = "";
        songBox.style.display = "none";
    } else {
        choosePlaylist.style.display = "none";
        songBox.style.display = "";
    }

}

function displayPlaylistSongs(playlistId, playlistName) {
    let name = document.getElementById('PlaylistLink');
    name.innerHTML += `<h1 style.float="left" >${playlistName}</h1>`;
    let url = 'http://localhost:8088/displayPlaylistSongs';
    let playlistmapper = {};
    let playlist = {};
    playlist.playlistId = playlistId;
    playlistmapper.playlist = playlist;
    displayPostApi(playlistmapper, url).then(function displaydata(val) {
        console.log(val);
        music = val;

        document.getElementById("songlist").innerHTML = val.map((song) =>
            `<div id="song" onclick="playsong(${song.musicId}); getSongImage('${song.musicId}'); ">
            
            <audio id="${song.musicId}">
                <source src="http://localhost:8088/musics/${song.musicFileName}" type="audio/mpeg">
            </audio>
            <div id="songinfo">
                <p>${song.musicName}</p>
                <p>${song.musicArtist}</p>
            </div>
            <svg xmlns="http://www.w3.org/2000/svg" id="morebtn" onclick="musicOptions(${song.musicId},${playlistId})" width="30" height="30" fill="currentColor" class="bi bi-three-dots-vertical" viewBox="0 0 16 16">
                <path d="M9.5 13a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z"/>
            </svg>
        </div>`
        ).join("")
    });
}

function musicOptions(musicId, playlistId) {
    let check = document.getElementById('musicOption');
    if (check != null) {
        check.remove();
        return;
    }
    let playingSongContainer = document.getElementById('playingSongContainer');
    let musicOption = document.createElement('div');
    musicOption.id = 'musicOption';
    playingSongContainer.prepend(musicOption);
    musicOption = document.getElementById('musicOption').innerHTML =
        `<div id="musicOptionText">
        <button id="" onclick="removeFromPlaylist(${musicId},${playlistId})">Remove From Playlist</button>
    </div>`


}

function getPlaylistNameInput() {
    let url = 'http://localhost:8088/createPlaylist';
    let playlistName = document.getElementById('PlistName').value
    let playlist = {};
    playlist.playlistName = playlistName;
    displayPostApiText(playlist, url).then(function displaydata(val) {
        alert(val);
        window.location = "http://localhost:8088/html/UserPlaylists.html"
    });

}

function createPlaylist() {
    let PlaylistName = document.getElementById('PlaylistName');
    console.log(PlaylistName)
    PlaylistName.innerHTML =
        `
    Enter Playlist Name
    <input id="PlistName" type="text"placeholder="Playlist Name"></input>
    <button id="submitName" onclick="getPlaylistNameInput()" >submit</button>
    
    `
}


function removeFromPlaylist(musicId, playlistId) {
    let url = 'http://localhost:8088/removeFromPlaylist';
    let input = {};
    let music = {};
    let playlist = {};
    music.musicId = musicId;
    playlist.playlistId = playlistId;
    input.music = music;
    input.playlist = playlist;
    displayPostApi(input, url).then(function displaydata(val) {
        document.getElementById("songlist").innerHTML = val.map((song) =>
            `<div id="song" onclick="playsong(${song.musicId}); getSongImage('${song.musicId}'); ">
            
            <audio id="${song.musicId}">
                <source src="http://localhost:8088/musics/${song.musicFileName}" type="audio/mpeg">
            </audio>
            <div id="songinfo">
                <p>${song.musicName}</p>
                <p>${song.musicArtist}</p>
            </div>
            <svg xmlns="http://www.w3.org/2000/svg" id="morebtn" onclick="musicOptions(${song.musicId},${playlistId})" width="30" height="30" fill="currentColor" class="bi bi-three-dots-vertical" viewBox="0 0 16 16">
                <path d="M9.5 13a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z"/>
            </svg>
        </div>`
        ).join("")

        let audio = document.getElementsByTagName('audio');
        console.log(audio[0].id)
        playsong(audio[0].id);
    });


}





function getSongImage(musicId) {
    let musicImage = "";

    music.map((music) => {
        if (music.musicId == musicId) {
            musicImage = music.musicImage;
        }
    })

    let playingSong = document.getElementById('playingSong');
    playingSong.innerHTML =
        `
    <img id="musicImage" src="http://localhost:8088/musicImages/${musicImage}" alt="">

    `
}

function playsong(musicId) {
    let audio = document.getElementById(musicId);

    let playbtn = document.getElementById('playbtn');
    let pausebtn = document.getElementById('pausebtn');
    document.getElementById('controlbar').innerHTML =

        //${parseInt((audio.currentTime / audio.duration) * 100)}
        `
    <input id="bar" onclick="progress(${musicId})" value="0" type="range">

        <svg xmlns="http://www.w3.org/2000/svg" id="backward" onclick="previoussong(${musicId})" width="40" height="40" fill="white"
            class="bi bi-skip-backward-fill" viewBox="0 0 16 16">
            <path
                d="M.5 3.5A.5.5 0 0 0 0 4v8a.5.5 0 0 0 1 0V8.753l6.267 3.636c.54.313 1.233-.066 1.233-.697v-2.94l6.267 3.636c.54.314 1.233-.065 1.233-.696V4.308c0-.63-.693-1.01-1.233-.696L8.5 7.248v-2.94c0-.63-.692-1.01-1.233-.696L1 7.248V4a.5.5 0 0 0-.5-.5z" />
        </svg>

        <svg xmlns="http://www.w3.org/2000/svg" id="forward" onclick="nextsong(${musicId})" width="40" height="40" fill="white"
            class="bi bi-skip-forward-fill" viewBox="0 0 16 16">
            <path
                d="M15.5 3.5a.5.5 0 0 1 .5.5v8a.5.5 0 0 1-1 0V8.753l-6.267 3.636c-.54.313-1.233-.066-1.233-.697v-2.94l-6.267 3.636C.693 12.703 0 12.324 0 11.693V4.308c0-.63.693-1.01 1.233-.696L7.5 7.248v-2.94c0-.63.693-1.01 1.233-.696L15 7.248V4a.5.5 0 0 1 .5-.5z" />
        </svg>

        <div id="playpause">
            <svg xmlns="http://www.w3.org/2000/svg" onclick="Play(${musicId})" id="playbtn" width="45" height="45"
                fill="white" class="bi bi-play-circle" viewBox="0 0 16 16">
                <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
                <path
                    d="M6.271 5.055a.5.5 0 0 1 .52.038l3.5 2.5a.5.5 0 0 1 0 .814l-3.5 2.5A.5.5 0 0 1 6 10.5v-5a.5.5 0 0 1 .271-.445z" />
            </svg>

            <svg xmlns="http://www.w3.org/2000/svg" onclick="Pause(${musicId})" id="pausebtn" width=45" height="45"
                fill="white" class="bi bi-pause-circle" viewBox="0 0 16 16">
                <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
                <path
                    d="M5 6.25a1.25 1.25 0 1 1 2.5 0v3.5a1.25 1.25 0 1 1-2.5 0v-3.5zm3.5 0a1.25 1.25 0 1 1 2.5 0v3.5a1.25 1.25 0 1 1-2.5 0v-3.5z" />
            </svg>

            
        </div>
    `

    if (audio.currentTime == audio.duration) {
        nextsong(musicId);
    }



    document.addEventListener('play', function (e) {
        var audios = document.getElementsByTagName('audio');
        for (var i = 0, len = audios.length; i < len; i++) {
            if (audios[i] != e.target) {
                audios[i].pause();
            }
        }
    }, true);
    audio.currentTime = 0
    Play(musicId);

}

function progress(musicId) {
    let bar = document.getElementById('bar');
    let audio = document.getElementById(musicId);
    audio.currentTime = (bar.value * audio.duration) / 100;
}

function previoussong(musicId) {
    let audios = document.getElementsByTagName('audio');
    let currentMusicIndex = 0;
    if (audios[0].id == musicId) {
        return;
    }
    Pause(musicId);
    for (var i = 0, len = audios.length; i < len; i++) {
        if (audios[i].id == musicId) {
            currentMusicIndex = i - 1;
            break;
        }
    }
    getSongImage(audios[currentMusicIndex].id);
    playsong(audios[currentMusicIndex].id);

}

function nextsong(musicId) {
    let audios = document.getElementsByTagName('audio');
    let currentMusicIndex = 0;
    Pause(musicId);

    for (var i = 0, len = audios.length; i < len; i++) {
        if (audios[audios.length - 1].id == musicId) {
            currentMusicIndex = 0;
            break;
        }
        if (audios[i].id == musicId) {
            currentMusicIndex = i + 1;
            break;
        }
    }
    getSongImage(audios[currentMusicIndex].id);
    playsong(audios[currentMusicIndex].id);

}


function Play(musicId) {

    let playbtn = document.getElementById('playbtn');
    let pausebtn = document.getElementById('pausebtn');
    let bar = document.getElementById('bar');
    playbtn.style.display = "none";
    pausebtn.style.display = "";
    let audio = document.getElementById(musicId);
    audio.play();
    audio.addEventListener('timeupdate', () => {
        bar.value = parseInt((audio.currentTime / audio.duration) * 100);
        if (audio.currentTime == audio.duration) {
            nextsong(musicId);
        }
    })

}
function Pause(musicId) {
    let playbtn = document.getElementById('playbtn');
    let pausebtn = document.getElementById('pausebtn');
    let bar = document.getElementById('bar');
    playbtn.style.display = "";
    pausebtn.style.display = "none";
    let audio = document.getElementById(musicId);

    audio.pause();
    audio.addEventListener('timeupdate', () => {
        bar.value = parseInt((audio.currentTime / audio.duration) * 100);
        if (audio.currentTime == audio.duration) {
            nextsong(musicId);
        }
    })
}
