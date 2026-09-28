import {
  FaHospital,
  FaHome,
  FaUserMd,
  FaUsers,
  FaBuilding,
  FaCalendarAlt,
  FaFilePrescription,
  FaMoneyBillWave,
  FaSignOutAlt,
} from "react-icons/fa";

import { NavLink } from "react-router-dom";

function Sidebar() {
  return (
    <div className="fixed left-0 top-0 w-72 h-screen bg-slate-900 text-white shadow-2xl">

      {/* Logo */}
      <div className="h-20 flex items-center px-8 border-b border-slate-700">

        <div className="w-12 h-12 bg-blue-600 rounded-xl flex items-center justify-center">
          <FaHospital className="text-2xl" />
        </div>

        <div className="ml-4">
          <h1 className="text-xl font-bold">HMS</h1>
          <p className="text-sm text-slate-400">
            Hospital Management
          </p>
        </div>

      </div>

      {/* Menu */}
      <div className="px-4 py-6">

        <ul className="space-y-2">

          {/* Dashboard */}

          <li>
            <NavLink
              to="/"
              end
              className={({ isActive }) =>
                `flex items-center gap-4 px-5 py-4 rounded-xl transition-all duration-300 ${
                  isActive
                    ? "bg-blue-600 text-white"
                    : "hover:bg-slate-800 text-slate-300"
                }`
              }
            >
              <FaHome className="text-lg" />
              <span>Reception</span>
            </NavLink>
          </li>

          {/* Doctors */}

          <li>
            <NavLink
              to="/doctors"
              className={({ isActive }) =>
                `flex items-center gap-4 px-5 py-4 rounded-xl transition-all duration-300 ${
                  isActive
                    ? "bg-blue-600 text-white"
                    : "hover:bg-slate-800 text-slate-300"
                }`
              }
            >
              <FaUserMd className="text-lg" />
              <span>Doctors</span>
            </NavLink>
          </li>

          {/* Patients */}

          <li>
            <NavLink
              to="/patients"
              className={({ isActive }) =>
                `flex items-center gap-4 px-5 py-4 rounded-xl transition-all duration-300 ${
                  isActive
                    ? "bg-blue-600 text-white"
                    : "hover:bg-slate-800 text-slate-300"
                }`
              }
            >
              <FaUsers className="text-lg" />
              <span>Patients</span>
            </NavLink>
          </li>

          {/* Departments */}

          <li>
            <NavLink
              to="/departments"
              className={({ isActive }) =>
                `flex items-center gap-4 px-5 py-4 rounded-xl transition-all duration-300 ${
                  isActive
                    ? "bg-blue-600 text-white"
                    : "hover:bg-slate-800 text-slate-300"
                }`
              }
            >
              <FaBuilding className="text-lg" />
              <span>Departments</span>
            </NavLink>
          </li>

          {/* Appointments */}

          <li>
            <NavLink
              to="/appointments"
              className={({ isActive }) =>
                `flex items-center gap-4 px-5 py-4 rounded-xl transition-all duration-300 ${
                  isActive
                    ? "bg-blue-600 text-white"
                    : "hover:bg-slate-800 text-slate-300"
                }`
              }
            >
              <FaCalendarAlt className="text-lg" />
              <span>Appointments</span>
            </NavLink>
          </li>

          {/* Billing */}

          <li>
            <NavLink
              to="/billing"
              className={({ isActive }) =>
                `flex items-center gap-4 px-5 py-4 rounded-xl transition-all duration-300 ${
                  isActive
                    ? "bg-blue-600 text-white"
                    : "hover:bg-slate-800 text-slate-300"
                }`
              }
            >
              <FaMoneyBillWave className="text-lg" />
              <span>Billings</span>
            </NavLink>
          </li>

          {/* Prescription */}

          <li>
            <NavLink
              to="/prescription"
              className={({ isActive }) =>
                `flex items-center gap-4 px-5 py-4 rounded-xl transition-all duration-300 ${
                  isActive
                    ? "bg-blue-600 text-white"
                    : "hover:bg-slate-800 text-slate-300"
                }`
              }
            >
              <FaFilePrescription className="text-lg" />
              <span>Prescriptions</span>
            </NavLink>
          </li>

        </ul>

      </div>

      {/* Bottom */}

      <div className="absolute bottom-6 left-4 right-4">

        <button
          className="w-full flex items-center justify-center gap-3 bg-red-600 hover:bg-red-700 py-4 rounded-xl transition-all duration-300"
        >
          <FaSignOutAlt />
          Logout
        </button>

      </div>

    </div>
  );
}

export default Sidebar;