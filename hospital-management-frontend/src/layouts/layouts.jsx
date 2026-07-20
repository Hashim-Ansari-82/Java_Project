import Sidebar from "./Sidebar";
import Navbar from "./Navbar";

function Layout({ children }) {

  return (

    <div className="min-h-screen bg-gray-100">

      <Sidebar />

      <Navbar />

      {/* Main Content */}
      <main className="ml-72 pt-16 p-6">

        {children}

      </main>

    </div>
  );
}

export default Layout;