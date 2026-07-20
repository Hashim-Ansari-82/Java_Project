import { BrowserRouter, Routes, Route } from "react-router-dom";

import Sidebar from "./components/Sidebar";
import Navbar from "./components/Navbar";

import Dashboard from "./pages/Dashboard";
import Doctors from "./pages/Doctors";
import Patients from "./pages/Patients";
import Departments from "./pages/Departments";
import Appointments from "./pages/Appointments";
import Billing from "./pages/Billing";
import Prescription from "./pages/Prescription";

function App() {
  return (
    <BrowserRouter>
      <div className="flex bg-slate-100">

        <Sidebar />

        <div className="ml-72 flex-1">

          <Navbar />

          <div className="p-8">

            <Routes>

              <Route path="/" element={<Dashboard />} />

              <Route path="/doctors" element={<Doctors />} />

              <Route path="/patients" element={<Patients />} />

              <Route path="/departments" element={<Departments />} />

              <Route path="/appointments" element={<Appointments />} />

              <Route path="/billing" element={<Billing />} />

              <Route path="/prescription" element={<Prescription />} />

            </Routes>

          </div>

        </div>

      </div>
    </BrowserRouter>
  );
}


export default App;