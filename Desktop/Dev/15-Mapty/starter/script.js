'use strict';

const form = document.querySelector('.form');
const containerWorkouts = document.querySelector('.workouts');
const inputType = document.querySelector('.form__input--type');
const inputDistance = document.querySelector('.form__input--distance');
const inputDuration = document.querySelector('.form__input--duration');
const inputCadence = document.querySelector('.form__input--cadence');
const inputElevation = document.querySelector('.form__input--elevation');

/////////////////////////

//! App classes
class Workout {
  date = new Date();
  //? Create arandom date and convert to string
  id = (Date.now() + '').slice(-10);

  constructor(coords, distance, duration) {
    this.coords = coords;
    this.distance = distance;
    this.duration = duration;
  }

  _setWorkoutDescription() {
    // prettier-ignore
    const months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];

    this.description = `${this.type[0].toUpperCase()}${this.type.slice(1)} on ${
      months[this.date.getMonth()]
    }
    ${this.date.getDate()}`;
  }
}
//? Child classes
class Running extends Workout {
  constructor(coords, distance, duration, cadence) {
    super(coords, distance, duration);
    this.cadence = cadence;
    this.type = 'running';
    this.calcPace();
    this._setWorkoutDescription();
  }

  //? Calculate the pace
  calcPace() {
    this.pace = this.duration / this.distance;
    return this.pace;
  }
}

class Cycling extends Workout {
  constructor(coords, distance, duration, elevationGain) {
    super(coords, distance, duration);
    this.elevationGain = elevationGain;
    this.type = 'cycling';
    this.calcSpeed();
    this._setWorkoutDescription();
  }

  //? Calculate speed
  calcSpeed() {
    this.speed = this.distance / (this.duration / 60);
    return this.speed;
  }
}

/////////////////////////////

//? APPLICATION ARCHITECTURE
class App {
  //! Private class vars
  #map;
  #mapEvent;
  #workoutsArr = [];

  constructor() {
    // ? Get current position
    this._getPosition();

    //? Display workout marker
    form.addEventListener('submit', this._newWorkout.bind(this));

    //? Toogle between elev gain and cadence between running and cycling
    inputType.addEventListener('change', this._toggleElevationField);
  }

  _getPosition() {
    //! Get and display the current user location

    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(this._loadMap.bind(this), () => {
        alert('Please enable your location settings to use this applciation.');
      });
    }
  }

  _loadMap(pos) {
    const { latitude } = pos.coords;
    const { longitude } = pos.coords;

    // console.log(`https://www.google.com/maps/@${latitude},${longitude}`);

    const coords = [latitude, longitude];

    this.#map = L.map('map').setView(coords, 13);

    L.tileLayer('https://{s}.tile.openstreetmap.fr/hot/{z}/{x}/{y}.png').addTo(
      this.#map
    );

    //? Leaflet on method instead of addEventListener so we can easy
    //? pinpoint the users click location
    this.#map.on('click', this._showForm.bind(this));
  }

  _showForm(e) {
    this.#mapEvent = e;
    form.classList.remove('hidden');
    inputDistance.focus();
  }

  _toggleElevationField() {
    inputElevation.closest('.form__row').classList.toggle('form__row--hidden');
    inputCadence.closest('.form__row').classList.toggle('form__row--hidden');
  }

  _newWorkout(e) {
    //* Helper function to check for valid inputs
    const isValidInput = (...nums) => nums.every(num => Number.isFinite(num));

    const isPositive = (...nums) => nums.every(num => num > 0);

    e.preventDefault();

    //* Get workout type
    const type = inputType.value;
    //* Convert the input to number
    const distance = +inputDistance.value;
    const duration = +inputDuration.value;
    const { lat, lng } = this.#mapEvent.latlng;
    let workout;

    //* Check if data is valid
    switch (type) {
      case 'running':
        const cadence = +inputCadence.value;
        if (
          !isValidInput(distance, duration, cadence) ||
          !isPositive(distance, duration, cadence)
        ) {
          return alert('Number must be positive value.');
        }

        workout = new Running([lat, lng], distance, duration, cadence);

        break;
      case 'cycling':
        const elevation = +inputElevation.value;

        if (
          !isValidInput(distance, duration, elevation) ||
          !isPositive(distance, duration, elevation)
        )
          return alert('Number must be positive value.');

        workout = new Cycling([lat, lng], distance, duration, elevation);

        break;

      default:
        break;
    }

    //* Add workout to the workout array
    this.#workoutsArr.push(workout);

    //* Display workout marker
    this._renderWorkoutMarker(workout);

    //* Render workout on list
    this._renderWorkout(workout);

    //* Hide input form
    this._hideWorkoutForm();
  }

  _renderWorkoutMarker(workout) {
    L.marker(workout.coords)
      .addTo(this.#map)
      .bindPopup(
        L.popup({
          maxWidth: 250,
          minWidth: 100,
          autoClose: false,
          closeOnClick: false,
          className: `${workout.type}-popup`,
        })
      )
      .setPopupContent(
        `${workout.type === 'running' ? '🏃‍♂️' : '🚴‍♀️'}${workout.description}`
      )
      .openPopup();
  }

  // ? Render workout on map
  _renderWorkout(workout) {
    let workoutHtml = ` <li class="workout workout--${workout.type}" data-id="${
      workout.id
    }">
    <h2 class="workout__title">${workout.description}</h2>
    <div class="workout__details">
      <span class="workout__icon">${
        workout.type === 'running' ? '🏃‍♂️' : '🚴‍♀️'
      }</span>
      <span class="workout__value">${workout.distance}</span>
      <span class="workout__unit">km</span>
    </div>
    <div class="workout__details">
      <span class="workout__icon">⏱</span>
      <span class="workout__value">${workout.duration}</span>
      <span class="workout__unit">min</span>
    </div>`;
    if (workout.type === 'running')
      workoutHtml += `<div class="workout__details">
      <span class="workout__icon">⚡️</span>
      <span class="workout__value">${workout.pace.toFixed(1)}</span>
      <span class="workout__unit">min/km</span>
    </div>
    <div class="workout__details">
      <span class="workout__icon">🦶🏼</span>
      <span class="workout__value">${workout.cadence}</span>
      <span class="workout__unit">spm</span>
    </div>
  </li>`;

    if (workout.type === 'cycling')
      workoutHtml += `<div class="workout__details">
      <span class="workout__icon">⚡️</span>
      <span class="workout__value">${workout.speed.toFixed(1)}</span>
      <span class="workout__unit">km/h</span>
    </div>
    <div class="workout__details">
      <span class="workout__icon">⛰</span>
      <span class="workout__value">${workout.elevationGain}</span>
      <span class="workout__unit">m</span>
    </div>
  </li>`;

    //* Insert the workout html
    form.insertAdjacentHTML('afterend', workoutHtml);
  }

  //? Hide form
  _hideWorkoutForm() {
    inputCadence.value =
      inputDistance.value =
      inputDuration.value =
      inputElevation.value =
        '';
    form.classList.remove('hidden');
  }
}

////////////////////////////

//! Create new app object
const app = new App();
