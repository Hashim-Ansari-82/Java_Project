import {
  FaBell,
  FaSearch,
  FaClock,
} from "react-icons/fa";
import adminPhoto from "../assets/admin.jpg";
import API from '../api/axiosConfig';
import { useEffect, useState, useRef } from "react";

function Navbar() {

  // State for Date & Time
  const [currentDate, setCurrentDate] = useState("");
  const [currentTime, setCurrentTime] = useState("");
  const [notificationCount, setNotificationCount] = useState(0);
  const [notifications, setNotifications] = useState([]);
  const [lastSeen, setLastSeen] = useState({
    appointmentId: 0,
    billingId: 0,
    prescriptionId: 0,
    doctorId: 0,
    departmentId: 0,
    patientId: 0,
  });

  const initializeNotifications = async () => {
    try {
      const [
        appointmentsRes,
        billingsRes,
        prescriptionsRes,
        doctorsRes,
        departmentsRes,
        patientsRes
      ] = await Promise.all([
        API.get('/appointments'),
        API.get('/billings'),
        API.get('/prescriptions'),
        API.get('/doctors'),
        API.get('/departments'),
        API.get('/patients'),
      ]);

      // Purane sab records ko "already seen" maan lo
      const latestIds = {
        appointmentId: Math.max(...appointmentsRes.data.map(a => a.id), 0),
        billingId: Math.max(...billingsRes.data.map(b => b.id), 0),
        prescriptionId: Math.max(...prescriptionsRes.data.map(p => p.id), 0),
        doctorId: Math.max(...doctorsRes.data.map(d => d.id), 0),
        departmentId: Math.max(...departmentsRes.data.map(d => d.id), 0),
        patientId: Math.max(...patientsRes.data.map(p => p.id), 0),
      };

      setLastSeen(latestIds);
      lastSeenRef.current = latestIds;

      // Bell first time empty
      setNotifications([]);
      setNotificationCount(0);

    } catch (error) {
      console.log(error);
    }
  };

  const notificationRef = useRef(null);

  const [showNotifications, setShowNotifications] = useState(false);

  const fetchNotifications = async () => {
    try {
      const [
        appointmentsRes,
        billingsRes,
        prescriptionsRes,
        doctorsRes,
        departmentsRes,
        patientsRes
      ] = await Promise.all([
        API.get('/appointments'),
        API.get('/billings'),
        API.get('/prescriptions'),
        API.get('/doctors'),
        API.get('/departments'),
        API.get('/patients'),
      ]);

      const notificationsList = [];

      // ===== NEW PENDING APPOINTMENTS =====
      appointmentsRes.data
       .filter(a => a.status === 'Pending' && a.id > lastSeenRef.current.appointmentId)
        .forEach(a => {
          notificationsList.push({
            id: `a-${a.id}`,
            message: `New pending appointment #${a.id}`,
            type: 'appointment'
          });
        });

      // ===== NEW BILLINGS =====
      billingsRes.data
        .filter(b => b.id > lastSeenRef.current.billingId)
        .forEach(b => {
          notificationsList.push({
            id: `b-${b.id}`,
            message: `New billing of ₹${b.amount} generated`,
            type: 'billing'
          });
        });

      // ===== NEW PRESCRIPTIONS =====
      prescriptionsRes.data
        .filter(p => p.id > lastSeenRef.current.prescriptionId)
        .forEach(p => {
          notificationsList.push({
            id: `p-${p.id}`,
            message: `New prescription added: ${p.medicine}`,
            type: 'prescription'
          });
        });

      if (notificationsList.length > 0) {
        setNotifications(notificationsList);
        setNotificationCount(notificationsList.length);

        // IMPORTANT: Latest IDs update karo
       const updatedIds = {
  appointmentId: Math.max(...appointmentsRes.data.map(a => a.id), lastSeenRef.current.appointmentId),
  billingId: Math.max(...billingsRes.data.map(b => b.id), lastSeenRef.current.billingId),
  prescriptionId: Math.max(...prescriptionsRes.data.map(p => p.id), lastSeenRef.current.prescriptionId),
  doctorId: Math.max(...doctorsRes.data.map(d => d.id), lastSeenRef.current.doctorId),
  departmentId: Math.max(...departmentsRes.data.map(d => d.id), lastSeenRef.current.departmentId),
  patientId: Math.max(...patientsRes.data.map(p => p.id), lastSeenRef.current.patientId),
};

setLastSeen(updatedIds);
lastSeenRef.current = updatedIds;
      }

    } catch (error) {
      console.log('Notification Error:', error);
    }
  };

  useEffect(() => {
    const handleClickOutside = (event) => {
      if (
        notificationRef.current &&
        !notificationRef.current.contains(event.target)
      ) {
        setShowNotifications(false);
      }
    };

    document.addEventListener("mousedown", handleClickOutside);

    return () => {
      document.removeEventListener(
        "mousedown",
        handleClickOutside
      );
    };
  }, []);

  // Fetch notifications
  useEffect(() => {
    // First time: old records ko seen mark karo
    initializeNotifications();

    // Sirf new changes check karo
    const interval = setInterval(fetchNotifications, 5000);

    return () => clearInterval(interval);
  }, []);

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
            <FaClock className="text-blue-600 text-lg" />
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

        {/* <!-- Notification --> */}
        <div className="relative" ref={notificationRef}>

          <button
            onClick={() => {
              const next = !showNotifications;
              setShowNotifications(next);

              // Bell open => notifications read ho gayi
              if (next) {
                setNotificationCount(0);
                setNotifications([]);
              }
            }}
            className="relative p-2 rounded-xl hover:bg-gray-100 transition"
          >

            <FaBell className="text-2xl text-gray-700" />

            {notificationCount > 0 && (
              <span
                className="
        absolute -top-1 -right-1
        bg-red-500 text-white text-xs
        min-w-[20px] h-5 px-1
        rounded-full flex items-center justify-center
        font-semibold shadow-md
      "
              >
                {notificationCount}
              </span>
            )}

          </button>

          {showNotifications && (
            <div className="
      absolute right-0 mt-3
      w-80 bg-white rounded-2xl
      shadow-2xl border border-gray-100
      z-50 overflow-hidden
    ">

              <div className="p-4 border-b bg-slate-50">
                <h3 className="font-bold text-slate-800">
                  Notifications
                </h3>
              </div>

              <div className="max-h-80 overflow-y-auto">

                {notifications.length === 0 ? (
                  <p className="p-4 text-gray-500 text-sm">
                    No new notifications
                  </p>
                ) : (
                  notifications.map((n) => (
                    <div
                      key={n.id}
                      className="p-4 border-b hover:bg-gray-50 transition"
                    >
                      <p className="text-sm text-slate-700">
                        {n.message}
                      </p>
                    </div>
                  ))
                )}

              </div>

            </div>
          )}

        </div>

        {/* <!-- Profile --> */}
        <div className="flex items-center gap-3">

          <div className="w-14 h-14 rounded-full overflow-hidden border-2 border-blue-500 shadow-md">
            <img
              src={adminPhoto}
              alt="Admin"
              className="w-full h-full object-cover"
            />
          </div>

          <div>
            <h2 className="font-semibold text-slate-800">
              Hashim Ansari
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