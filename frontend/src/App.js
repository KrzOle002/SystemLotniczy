import { BrowserRouter,Routes,Route } from 'react-router-dom';
import './App.css';
import Home from "./pages/Home";
import Reservation from "./pages/Reservation";
import FlightList from './pages/FlightList';
import SeatList from './pages/SeatList';
import { ToastContainer } from 'react-toastify';
import NavComponent from './components/NavComponent';

function App() {
  return (
    <BrowserRouter>
    <div className='app'>
    <NavComponent/>
      <ToastContainer position="top-center"/>
      <Routes>
        <Route path="/" element={ <Home/> }/>
        <Route path="/add" element={<Reservation/>}/>
        <Route path="/reservations" element={<FlightList/>}/>
        <Route path="/seats" element={<SeatList/>}/>
      </Routes>
    </div>
    </BrowserRouter>
  );
}

export default App;
