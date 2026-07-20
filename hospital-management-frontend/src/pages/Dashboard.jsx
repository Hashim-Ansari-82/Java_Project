import { useEffect, useState } from "react";
import {
  FaUserMd,
  FaUsers,
  FaBuilding,
  FaCalendarCheck,
  FaPrescriptionBottleAlt,
  FaFileInvoiceDollar,
} from "react-icons/fa";

import API from "../api/axiosConfig";
import DashboardCard from "../components/DashboardCard";

function Dashboard() {
  const [stats, setStats] = useState({
    totalDoctors: 0,
    totalPatients: 0,
    totalDepartments: 0,
    totalAppointments: 0,
    totalPrescriptions: 0,
    totalBillings: 0,
  });

  const [loading, setLoading] = useState(true);
const [currentTime, setCurrentTime] = useState("");
  useEffect(() => {
  loadDashboardStats();

  // Live Clock
  const updateTime = () => {
    const time = new Date().toLocaleTimeString("en-IN", {
      hour: "2-digit",
      minute: "2-digit",
      second: "2-digit",
      hour12: true,
    });

    setCurrentTime(time);
  };

  updateTime();

  const interval = setInterval(updateTime, 1000);

  return () => clearInterval(interval);
}, []);

  const loadDashboardStats = async () => {
    try {
      setLoading(true);

      const [
        doctorsRes,
        patientsRes,
        departmentsRes,
        appointmentsRes,
        prescriptionsRes,
        billingsRes,
      ] = await Promise.all([
        API.get("/doctors"),
        API.get("/patients"),
        API.get("/departments"),
        API.get("/appointments"),
        API.get("/prescriptions"),
        API.get("/billings"),
      ]);

      setStats({
        totalDoctors: doctorsRes.data.length || 0,
        totalPatients: patientsRes.data.length || 0,
        totalDepartments: departmentsRes.data.length || 0,
        totalAppointments: appointmentsRes.data.length || 0,
        totalPrescriptions: prescriptionsRes.data.length || 0,
        totalBillings: billingsRes.data.length || 0,
      });
    } catch (error) {
      console.error("Dashboard stats error:", error);
    } finally {
      setLoading(false);
    }
  };

  if (loading) {
    return (
      <div className="flex justify-center items-center h-[70vh]">
        <div className="text-center">
          <div className="w-14 h-14 border-4 border-blue-500 border-t-transparent rounded-full animate-spin mx-auto mb-4"></div>
          <p className="text-gray-600 font-semibold text-lg">
            Loading dashboard...
          </p>
        </div>
      </div>
    );
  }

  const today = new Date().toLocaleDateString("en-IN", {
    weekday: "long",
    day: "numeric",
    month: "long",
    year: "numeric",
  });

  return (
    <div className="min-h-screen bg-gray-50 p-4 md:p-6 space-y-6">

      {/* Header */}
      <div className="flex flex-col lg:flex-row lg:items-center lg:justify-between gap-4">

        <div>
          <h1 className="text-3xl md:text-4xl font-bold text-slate-800 tracking-tight">
            Hospital Dashboard
          </h1>

          <p className="text-gray-500 mt-1 text-sm md:text-base">
            Real-time hospital analytics overview
          </p>
        </div>

       <div className="bg-white border border-gray-200 rounded-2xl px-5 py-3 shadow-sm w-fit min-w-[220px]">

  <p className="text-xs text-gray-500 uppercase tracking-wide">
    Today
  </p>

  <p className="font-semibold text-slate-700 mt-1">
    {today}
  </p>

</div>
      </div>

      {/* Quick Summary */}
      <div className="grid grid-cols-1 md:grid-cols-3 gap-4">

        <div className="bg-white rounded-2xl p-5 shadow-sm border border-gray-100">
          <p className="text-sm text-gray-500">Today's Appointments</p>
          <h3 className="text-3xl font-bold text-slate-800 mt-2">
            {stats.totalAppointments}
          </h3>
          <p className="text-green-600 text-sm mt-1">Live tracking enabled</p>
        </div>

        <div className="bg-white rounded-2xl p-5 shadow-sm border border-gray-100">
          <p className="text-sm text-gray-500">Active Doctors</p>
          <h3 className="text-3xl font-bold text-slate-800 mt-2">
            {stats.totalDoctors}
          </h3>
          <p className="text-blue-600 text-sm mt-1">All departments connected</p>
        </div>

        <div className="bg-white rounded-2xl p-5 shadow-sm border border-gray-100">
          <p className="text-sm text-gray-500">Patient Records</p>
          <h3 className="text-3xl font-bold text-slate-800 mt-2">
            {stats.totalPatients}
          </h3>
          <p className="text-purple-600 text-sm mt-1">Synced with backend</p>
        </div>
      </div>

      {/* Main Analytics Cards */}
      <div className="grid grid-cols-1 sm:grid-cols-2 xl:grid-cols-3 gap-5">

        <DashboardCard
          title="Total Doctors"
          total={stats.totalDoctors}
          growth="Live Data"
          icon={<FaUserMd />}
          color="from-blue-500 via-blue-600 to-indigo-700"
        />

        <DashboardCard
          title="Total Patients"
          total={stats.totalPatients}
          growth="Live Data"
          icon={<FaUsers />}
          color="from-emerald-500 via-green-600 to-teal-700"
        />

        <DashboardCard
          title="Departments"
          total={stats.totalDepartments}
          growth="Live Data"
          icon={<FaBuilding />}
          color="from-orange-400 via-orange-500 to-amber-600"
        />

        <DashboardCard
          title="Appointments"
          total={stats.totalAppointments}
          growth="Live Data"
          icon={<FaCalendarCheck />}
          color="from-rose-500 via-red-500 to-pink-600"
        />

        <DashboardCard
          title="Prescriptions"
          total={stats.totalPrescriptions}
          growth="Live Data"
          icon={<FaPrescriptionBottleAlt />}
          color="from-cyan-500 via-sky-600 to-blue-700"
        />

        <DashboardCard
          title="Billing"
          total={stats.totalBillings}
          growth="Live Data"
          icon={<FaFileInvoiceDollar />}
          color="from-violet-500 via-purple-600 to-fuchsia-700"
        />

      </div>

    </div>
  );
}

export default Dashboard;