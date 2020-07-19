FROM kperson/ubuntu-open-gl

RUN apt-get install \
    ffmpeg \
    vlc \
    build-essential \
    checkinstall \
    cmake \
    git \
    libmbedtls-dev \
    libasound2-dev \
    libavcodec-dev \
    libavdevice-dev \
    libavfilter-dev \
    libavformat-dev \
    libavutil-dev \
    libcurl4-openssl-dev \
    libfdk-aac-dev \
    libfontconfig-dev \
    libfreetype6-dev \
    libgl1-mesa-dev \
    libjack-jackd2-dev \
    libjansson-dev \
    libluajit-5.1-dev \
    libpulse-dev \
    libqt5x11extras5-dev \
    libspeexdsp-dev \
    libswresample-dev \
    libswscale-dev \
    libudev-dev \
    libv4l-dev \
    libvlc-dev \
    libx11-dev \
    libx264-dev \
    libxcb-shm0-dev \
    libxcb-xinerama0-dev \
    libxcomposite-dev \
    libxinerama-dev \
    pkg-config \
    python3-dev \
    qtbase5-dev \
    libqt5svg5-dev \
    swig \
    libxcb-randr0-dev \
    libxcb-xfixes0-dev \
    libx11-xcb-dev \
    libxcb1-dev \
    wget -y && \
    apt-get clean

RUN wget https://cdn-fastly.obsproject.com/downloads/cef_binary_3770_linux64.tar.bz2 && \
    tar -xjf ./cef_binary_3770_linux64.tar.bz2 && \
    git clone --recursive https://github.com/obsproject/obs-studio.git && \
    cd obs-studio && \
    mkdir build && cd build && \
    cmake -DUNIX_STRUCTURE=1 -DCMAKE_INSTALL_PREFIX=/usr -DBUILD_BROWSER=ON -DCEF_ROOT_DIR="../../cef_binary_3770_linux64" .. && \
    make -j4 && \
    checkinstall --default --pkgname=obs-studio --fstrans=no --backup=no --pkgversion="$(date +%Y%m%d)-git" --deldoc=yes && \
    cd ../.. && rm -rf obs-studio

ADD obs-websocket-4.8.0-1_amd64.deb /obs-websocket-4.8.0-1_amd64.deb
RUN apt install /obs-websocket-4.8.0-1_amd64.deb -y && \
    rm /obs-websocket-4.8.0-1_amd64.deb && \
    apt clean

EXPOSE 4444
CMD ["obs"]