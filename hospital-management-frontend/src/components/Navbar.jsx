import { useEffect, useState } from "react";
import {
  FaBell,
  FaSearch,
  FaUserCircle,
  FaClock,
} from "react-icons/fa";

function Navbar() {

  // State for Date & Time
  const [currentDate, setCurrentDate] = useState("");
  const [currentTime, setCurrentTime] = useState("");

  // Live Clock
  useEffect(() => {

    const updateDateTime = () => {

      const now = new Date();

      // Date
      const date = now.toLocaleDateString("en-IN", {
        weekday: "short",
        day: "2-digit",
        month: "short",
        year: "numeric",
      });

      // Time
      const time = now.toLocaleTimeString("en-IN", {
        hour: "2-digit",
        minute: "2-digit",
        second: "2-digit",
        hour12: true,
      });

      setCurrentDate(date);
      setCurrentTime(time);
    };

    // Initial call
    updateDateTime();

    // Update every second
    const interval = setInterval(updateDateTime, 1000);

    // Cleanup
    return () => clearInterval(interval);

  }, []);

  return (

    <div className="h-20 bg-white shadow-sm flex justify-between items-center px-8 border-b border-gray-200">

      {/* Left */}

      <div>

        <h1 className="text-3xl font-bold text-slate-800">
          Dashboard
        </h1>

        <p className="text-gray-500">
          Welcome Back, Admin 👋
        </p>

      </div>

      {/* Right */}

      <div className="flex items-center gap-6">

        {/* Search */}

        <div className="relative">

          <FaSearch
            className="absolute left-4 top-4 text-gray-400"
          />

          <input
            type="text"
            placeholder="Search..."
            className="w-72 pl-12 pr-4 py-3 rounded-xl border border-gray-300 outline-none focus:border-blue-500"
          />

        </div>

        {/* Date & Time */}

        <div className="hidden lg:flex items-center gap-3 bg-slate-50 border border-gray-200 rounded-2xl px-4 py-2 min-w-[220px]">

          <div className="w-10 h-10 rounded-xl bg-blue-100 flex items-center justify-center">
            <FaClock className="text-blue-600 text-lg"/>
          </div>

          <div className="leading-tight">

            <p className="text-xs text-gray-500 font-medium">
              {currentDate}
            </p>

            <p className="text-lg font-bold text-slate-800 tracking-wide">
              {currentTime}
            </p>

          </div>

        </div>

        {/* Notification */}

        <div className="relative cursor-pointer">

          <FaBell className="text-2xl text-gray-700"/>

          <span className="absolute -top-2 -right-2 bg-red-500 text-white text-xs w-5 h-5 rounded-full flex justify-center items-center">

            3

          </span>

        </div>

        {/* Profile */}

        <div className="flex items-center gap-3">

          <FaUserCircle className="text-5xl text-blue-600"/>

          <div>

            <h2 className="font-semibold">
              Admin
            </h2>

            <p className="text-sm text-gray-500">
              Administrator
            </p>

          </div>

        </div>

      </div>

    </div>
  );
}

export default Navbar;