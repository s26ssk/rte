let renderer,
    scene,
    camera,
    nucleus,
    stars,
    controls,
    container = document.getElementById("canvas_container");
init();
animate();

function init() {
    scene = new THREE.Scene();

    const nucleusSize = 60;

    camera = new THREE.PerspectiveCamera(55, window.innerWidth / window.innerHeight, 1, 1000);
    camera.position.set(160, 10, 240);

    const directionalLight = new THREE.DirectionalLight("#ffffff", 0.6);
    directionalLight.position.set(0, 50, 5);
    scene.add(directionalLight);

    let ambientLight = new THREE.AmbientLight("#ffffff", 0.8);
    ambientLight.position.set(20, 0, 20);
    scene.add(ambientLight);

    renderer = new THREE.WebGLRenderer({
        antialias: true,
        alpha: true,
    });
    renderer.setSize(container.clientWidth, container.clientHeight);
    renderer.setPixelRatio(window.devicePixelRatio);
    container.appendChild(renderer.domElement);

    controls = new THREE.OrbitControls(camera, renderer.domElement);
    controls.autoRotate = true;
    controls.autoRotateSpeed = 2;
    controls.maxDistance = 350;
    controls.minDistance = 150;
    controls.enablePan = false;

    const loader = new THREE.TextureLoader();
    const texturenucleus = loader.load("https://i.ibb.co/pWngqLb/map.png");
    const textureStar = loader.load("https://i.ibb.co/ZKsdYSz/p1-g3zb2a.png");

    texturenucleus.anisotropy = 16;
    let icosahedronGeometry = new THREE.IcosahedronGeometry(nucleusSize, 10); // Sử dụng kích thước cố định
    let lambertMaterial = new THREE.MeshPhongMaterial({ map: texturenucleus });
    nucleus = new THREE.Mesh(icosahedronGeometry, lambertMaterial);
    scene.add(nucleus);

    let starsGeometry = new THREE.Geometry();

    for (let i = 0; i < 50; i++) {
        let particleStar = randomPointSphere(150);

        particleStar.velocity = THREE.MathUtils.randInt(50, 200);

        particleStar.startX = particleStar.x;
        particleStar.startY = particleStar.y;
        particleStar.startZ = particleStar.z;

        starsGeometry.vertices.push(particleStar);
    }
    let starsMaterial = new THREE.PointsMaterial({
        size: 5,
        color: "#91e37f",
        transparent: true,
        opacity: 4,
        map: textureStar,
        blending: THREE.AdditiveBlending,
    });
    starsMaterial.depthWrite = false;
    stars = new THREE.Points(starsGeometry, starsMaterial);
    scene.add(stars);

    function randomPointSphere(radius) {
        let theta = 2 * Math.PI * Math.random();
        let phi = Math.acos(2 * Math.random() - 1);
        let dx = 0 + (radius * Math.sin(phi) * Math.cos(theta));
        let dy = 0 + (radius * Math.sin(phi) * Math.sin(theta));
        let dz = 0 + (radius * Math.cos(phi));
        return new THREE.Vector3(dx, dy, dz);
    }
}

function animate() {
    stars.geometry.vertices.forEach(function (v) {
        v.x += (0 - v.x) / v.velocity;
        v.y += (0 - v.y) / v.velocity;
        v.z += (0 - v.z) / v.velocity;

        v.velocity -= 0.3;

        if (v.x <= 5 && v.x >= -5 && v.z <= 5 && v.z >= -5) {
            v.x = v.startX;
            v.y = v.startY;
            v.z = v.startZ;
            v.velocity = THREE.MathUtils.randInt(50, 300);
        }
    });

    controls.update();
    stars.geometry.verticesNeedUpdate = true;
    renderer.render(scene, camera);
    requestAnimationFrame(animate);
}



let myIndex = 0;
let myVideos = [
    "../images/video2.mp4",
    "../images/video1.mp4",
];

function backgroundChanger() {
    myIndex += 1;
    if (myIndex >= myVideos.length) myIndex = 0;
    document.getElementById("myVideo").src = myVideos[myIndex];
}

const load = document.getElementById('load');
window.addEventListener('load', function loadDefFrame() {
    setTimeout(function () {
        load.style.display = 'none';
    }, 1000);
});


function myFunction() {
    document.getElementById("myDropdown").classList.toggle("show");
}

window.onclick = function(e) {
    if (!e.target.matches('.dropbtn')) {
        var myDropdown = document.getElementById("myDropdown");
        if (myDropdown.classList.contains('show')) {
            myDropdown.classList.remove('show');
        }
    }
}