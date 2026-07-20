import { useEffect, useState } from "react";
import {
    FaPlus,
    FaSearch,
    FaEdit,
    FaTrash,
    FaCalendarCheck,
    FaClock,
    FaCheckCircle
} from "react-icons/fa";
import API from "../api/axiosConfig";


function Appointments() {


    const [appointments, setAppointments] = useState([]);
    const [doctors, setDoctors] = useState([]);
    const [patients, setPatients] = useState([]);

    const [search, setSearch] = useState("");

    const [open, setOpen] = useState(false);

    const [edit, setEdit] = useState(false);


    const [appointment, setAppointment] = useState({

        id: null,
        date: "",
        time: "",
        status: "",
        doctorId: "",
        patientId: ""

    });



    useEffect(() => {

        getAppointments();
        getDoctors();
        getPatients();
        getDepartments();
    }, []);





    // GET ALL APPOINTMENTS

    const getAppointments = async () => {

        try {

            const res = await API.get("/appointments");

            setAppointments(res.data);

        }
        catch (error) {

            console.log(error);

        }

    }





    // GET DOCTORS

    const getDoctors = async () => {

        try {

            const res = await API.get("/doctors");

            setDoctors(res.data);

        }
        catch (error) {

            console.log(error);

        }

    }

    const getDepartments = async () => {
        try {
            const response = await API.get('/departments');
            setDepartments(response.data);
        } catch (error) {
            console.log(error);
        }
    };



    // GET PATIENTS

    const getPatients = async () => {

        try {

            const res = await API.get("/patients");

            setPatients(res.data);

        }
        catch (error) {

            console.log(error);

        }

    }

    const handleChange = (e) => {

        const { name, value } = e.target;


        setAppointment({

            ...appointment,

            [name]: value

        });

    };


    // SAVE / UPDATE

    const handleSubmit = async (e) => {

        e.preventDefault();

        try {

            const data = {

                date: appointment.date,

                time: appointment.time,

                status: appointment.status,

                doctorId: Number(appointment.doctorId),

                patientId: Number(appointment.patientId)

            };


            console.log("Sending Data:", data);


            if (edit) {

                await API.put(
                    `/appointments/${appointment.id}`,
                    data
                );

            }
            else {

                await API.post(
                    "/appointments",
                    data
                );

            }


            closeForm();

            getAppointments();


        }
        catch (error) {

            console.log(
                "Backend Error:",
                error.response?.data
            );

        }

    };





    // EDIT

    const editAppointment = (data) => {


        setAppointment({

            id: data.id,

            date: data.date,

            time: data.time,

            status: data.status,

            doctorId: data.doctorId || "",

            patientId: data.patientId || ""

        });


        setEdit(true);

        setOpen(true);

    };




    // DELETE

    const deleteAppointment = async (id) => {


        if (!window.confirm("Delete Appointment?"))
            return;


        try {

            await API.delete(`/appointments/${id}`);

            getAppointments();

        }
        catch (error) {

            console.log(error);

        }


    }





    const closeForm = () => {


        setOpen(false);

        setEdit(false);


        setAppointment({

            id: null,
            date: "",
            time: "",
            status: "",
            doctorId: "",
            patientId: ""

        });


    }





    const filteredAppointments = appointments.filter((item) =>

        item.status
            ?.toLowerCase()
            .includes(search.toLowerCase())

    );

    const totalAppointments = appointments.length;
    const pendingAppointments = appointments.filter(a => a.status === "Pending").length;
    const confirmedAppointments = appointments.filter(a => a.status === "Confirmed").length;
    const completedAppointments = appointments.filter(a => a.status === "Completed").length;

    const appointmentStats = [
        {
            title: "Total Appointments",
            count: totalAppointments,
            icon: <FaCalendarCheck />,
            bg: "from-blue-500 to-indigo-600",
        },
        {
            title: "Pending",
            count: pendingAppointments,
            icon: <FaClock />,
            bg: "from-yellow-500 to-orange-600",
        },
        {
            title: "Confirmed",
            count: confirmedAppointments,
            icon: <FaCheckCircle />,
            bg: "from-emerald-500 to-green-600",
        },
        {
            title: "Completed",
            count: completedAppointments,
            icon: <FaCheckCircle />,
            bg: "from-purple-500 to-indigo-600",
        },
    ];

    return (

        <div className="p-6 bg-gray-100 min-h-screen">


            {/* HEADER */}

            <div className="flex justify-between items-center mb-5">


                <div>

                    <h1 className="text-3xl font-bold text-slate-800">
                        Appointments
                    </h1>

                    <p className="text-gray-500">
                        Manage patient appointments
                    </p>

                </div>


                <button

                    onClick={() => setOpen(true)}

                    className="
bg-blue-600
text-white
px-5
py-2
rounded-lg
flex
gap-2
items-center
hover:bg-blue-700
">

                    <FaPlus />

                    Add Appointment

                </button>


            </div>






            {/* SEARCH */}

            <div className="
bg-white
p-3
rounded-xl
shadow
mb-5
flex
items-center
gap-3
">


                <FaSearch className="text-gray-400" />


                <input

                    placeholder="Search status..."

                    value={search}

                    onChange={(e) => setSearch(e.target.value)}

                    className="
w-full
outline-none
"

                />


            </div>

            {/* <!-- APPOINTMENT STATISTICS CARDS --> */}
            <div className="grid grid-cols-2 md:grid-cols-4 gap-3 mb-5">

                {appointmentStats.map((item, index) => (

                    <div
                        key={index}
                        className={`
        bg-gradient-to-r ${item.bg}
        rounded-2xl
        shadow-md
        px-3 py-2.5
        text-white
        border border-white/10
        transition-all duration-300
        hover:-translate-y-0.5
        hover:shadow-xl
        cursor-pointer
        min-h-[78px]
      `}
                    >

                        <div className="flex justify-between items-center h-full">

                            <div className="flex-1 min-w-0">

                                <p className="text-[10px] md:text-[11px] opacity-90 font-semibold uppercase tracking-wider truncate">
                                    {item.title}
                                </p>

                                <h1 className="text-xl md:text-2xl font-extrabold mt-1 leading-none">
                                    {item.count}
                                </h1>

                            </div>

                            <div
                                className="
            w-9 h-9 md:w-10 md:h-10
            rounded-xl
            bg-white/20
            flex items-center justify-center
            text-lg md:text-xl
            backdrop-blur-sm
            flex-shrink-0
            ml-3
          "
                            >
                                {item.icon}
                            </div>

                        </div>

                    </div>

                ))}

            </div>




            {/* TABLE */}

            <div className="
bg-white
rounded-xl
shadow
overflow-hidden
">


                <table className="w-full">


                    <thead className="bg-blue-600 text-white">

                        <tr>

                            <th className="p-3">
                                ID
                            </th>

                            <th>
                                Date
                            </th>

                            <th>
                                Time
                            </th>

                            <th>
                                Doctor
                            </th>

                            <th>
                                Patient
                            </th>

                            <th>
                                Status
                            </th>

                            <th>
                                Action
                            </th>

                        </tr>

                    </thead>



                    <tbody>


                        {
                            filteredAppointments.map((item) => (


                                <tr
                                    key={item.id}
                                    className="
border-b
hover:bg-blue-50
"
                                >


                                    <td className="text-center p-3">
                                        {item.id}
                                    </td>


                                    <td className="text-center">
                                        {item.date}
                                    </td>


                                    <td className="text-center">
                                        {item.time}
                                    </td>


                                    <td className="text-center">
                                        {item.doctorName}
                                    </td>


                                    <td className="text-center">
                                        {item.patientName}
                                    </td>


                                    <td className="text-center">

                                        <span className="
bg-green-100
text-green-700
px-3
py-1
rounded-full
">

                                            {item.status}

                                        </span>

                                    </td>



                                    <td className="text-center">


                                        <button

                                            onClick={() => editAppointment(item)}

                                            className="
text-blue-600
mr-4
">

                                            <FaEdit />

                                        </button>



                                        <button

                                            onClick={() => deleteAppointment(item.id)}

                                            className="text-red-600"
                                        >

                                            <FaTrash />

                                        </button>



                                    </td>


                                </tr>


                            ))

                        }



                    </tbody>


                </table>


            </div>







            {/* FORM MODAL */}


            {

                open &&

                <div className="
fixed
inset-0
bg-black/40
flex
justify-center
items-center
">


                    <div className="
bg-white
w-[420px]
rounded-xl
shadow-lg
p-5
">


                        <h2 className="
text-xl
font-bold
mb-4
">

                            {
                                edit ?
                                    "Update Appointment" :
                                    "Add Appointment"

                            }

                        </h2>





                        <form onSubmit={handleSubmit} className="space-y-3">



                            <div className="grid grid-cols-2 gap-3">


                                <input
                                    type="text"
                                    name="date"
                                    placeholder="YYYY-MM-DD"
                                    value={appointment.date}
                                    onChange={(e) => {

                                        let value = e.target.value
                                            .replace(/[^\d-]/g, "")
                                            .slice(0, 10);

                                        // Auto add hyphens
                                        if (value.length === 4 && !value.includes("-")) {
                                            value = value + "-";
                                        } else if (value.length === 7 && value.split("-").length === 2) {
                                            value = value + "-";
                                        }

                                        setAppointment({
                                            ...appointment,
                                            date: value
                                        });
                                    }}
                                    pattern="\d{4}-\d{2}-\d{2}"
                                    className="border rounded-lg p-2 w-full"
                                    required
                                />




                                <input
                                    type="text"
                                    name="time"
                                    placeholder="HH:mm"
                                    value={appointment.time}
                                    onChange={(e) => {

                                        // Only allow HH:mm format
                                        let value = e.target.value
                                            .replace(/[^\d:]/g, "")
                                            .slice(0, 5);

                                        // Auto add colon after 2 digits
                                        if (value.length === 2 && !value.includes(":")) {
                                            value = value + ":";
                                        }

                                        setAppointment({
                                            ...appointment,
                                            time: value
                                        });
                                    }}
                                    pattern="([01][0-9]|2[0-3]):[0-5][0-9]"
                                    className="border rounded-lg p-2 w-full"
                                    required
                                />


                            </div>





                            <select

                                name="status"

                                value={appointment.status}

                                onChange={handleChange}

                                className="
w-full
border
rounded-lg
p-2
"

                                required

                            >


                                <option value="">
                                    Select Status
                                </option>

                                <option value="Pending">
                                    Pending
                                </option>

                                <option value="Confirmed">
                                    Confirmed
                                </option>

                                <option value="Completed">
                                    Completed
                                </option>

                                <option value="Cancelled">
                                    Cancelled
                                </option>


                            </select>







                            <select

                                name="doctorId"

                                value={appointment.doctorId}

                                onChange={handleChange}

                                className="
w-full
border
rounded-lg
p-2
"

                                required

                            >


                                <option value="">
                                    Select Doctor
                                </option>


                                {

                                    doctors.map((doc) => (

                                        <option
                                            key={doc.id}
                                            value={doc.id}
                                        >

                                            {doc.name}

                                        </option>

                                    ))

                                }


                            </select>







                            <select

                                name="patientId"

                                value={appointment.patientId}

                                onChange={handleChange}

                                className="
w-full
border
rounded-lg
p-2
"

                                required

                            >


                                <option value="">
                                    Select Patient
                                </option>


                                {

                                    patients.map((p) => (

                                        <option
                                            key={p.id}
                                            value={p.id}
                                        >

                                            {p.name}

                                        </option>

                                    ))

                                }


                            </select>







                            <div className="
flex
justify-end
gap-2
">


                                <button

                                    type="button"

                                    onClick={closeForm}

                                    className="
bg-gray-300
px-4
py-2
rounded-lg
">

                                    Cancel

                                </button>



                                <button

                                    className="
bg-blue-600
text-white
px-5
py-2
rounded-lg
">

                                    Save

                                </button>


                            </div>



                        </form>


                    </div>


                </div>

            }



        </div>


    );

}


export default Appointments;