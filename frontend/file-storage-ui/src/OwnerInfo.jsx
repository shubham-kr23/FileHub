function OwnerInfo({
  name = "Shubham Mandal",
  email = "shubhamkrm23@gmail.com",
  github = "https://github.com/shubham-kr23",
  linkedin = "https://www.linkedin.com/in/shubham-kr23/",
}) {
  return (
    <div
      style={{
        padding: "20px",
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        gap: "8px",
      }}
    >
      <h3 style={{ margin: 0 }}>{name}</h3>

      <div style={{ display: "flex", gap: "12px", marginTop: "8px" }}>
        <a
        href={`mailto:${email}`}
        style={{
          color: "inherit",
          display: "inline-flex",
          gap: "8px",
          alignItems: "center",
          textDecoration: "none",
        }}
        aria-label={`Email ${name}`}
        title={`Email ${name}`}
      >
        {/* Email icon */}
          <svg height="20" width="20" viewBox="0 0 24 24" fill="currentColor" aria-hidden="true" style={{ width: "20px", height: "20px", display: "block", flexShrink: 0 }}>
          <path d="M2 4a2 2 0 0 1 2-2h16a2 2 0 0 1 2 2v16a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V4zm2 2v.511l8 5.333 8-5.333V6H4zm0 12h16V9.333l-8 5.333-8-5.333V18z" />
        </svg>
        {/* <span>{email}</span> */}
      </a>
        
        <a
          href={github}
          target="_blank"
          rel="noopener noreferrer"
          aria-label="GitHub"
          title="GitHub"
          style={{ color: "inherit", display: "inline-flex", alignItems: "center" }}
        >
          {/* GitHub icon */}
          <svg height="20" width="20" viewBox="0 0 16 16" fill="currentColor" aria-hidden="true" style={{ width: "20px", height: "20px", display: "block", flexShrink: 0 }}>
            <path d="M8 0C3.58 0 0 3.58 0 8c0 3.54 2.29 6.53 5.47 7.59.4.07.55-.17.55-.38 0-.19-.01-.82-.01-1.49-2.01.37-2.53-.49-2.69-.94-.09-.23-.48-.94-.82-1.13-.28-.15-.68-.52-.01-.53.63-.01 1.08.58 1.23.82.72 1.21 1.87.87 2.33.66.07-.52.28-.87.51-1.07-1.78-.2-3.64-.89-3.64-3.95 0-.87.31-1.59.82-2.15-.08-.2-.36-1.02.08-2.12 0 0 .67-.21 2.2.82a7.6 7.6 0 0 1 4 0c1.53-1.03 2.2-.82 2.2-.82.44 1.1.16 1.92.08 2.12.51.56.82 1.27.82 2.15 0 3.07-1.87 3.75-3.65 3.95.29.25.54.73.54 1.48 0 1.07-.01 1.93-.01 2.2 0 .21.15.46.55.38A8.013 8.013 0 0 0 16 8c0-4.42-3.58-8-8-8z" />
          </svg>
        </a>

        <a
          href={linkedin}
          target="_blank"
          rel="noopener noreferrer"
          aria-label="LinkedIn"
          title="LinkedIn"
          style={{ color: "inherit", display: "inline-flex", alignItems: "center" }}
        >
          {/* LinkedIn icon */}
          <svg height="20" width="20" viewBox="0 0 24 24" fill="currentColor" aria-hidden="true" style={{ width: "20px", height: "20px", display: "block", flexShrink: 0 }}>
            <path d="M19 0h-14c-2.761 0-5 2.239-5 5v14c0 2.761 2.239 5 5 5h14c2.762 0 5-2.239 5-5v-14c0-2.761-2.238-5-5-5zm-11 19h-3v-10h3v10zm-1.5-11.268c-.966 0-1.75-.79-1.75-1.763 0-.973.784-1.764 1.75-1.764s1.75.791 1.75 1.764c0 .973-.783 1.763-1.75 1.763zm13.5 11.268h-3v-5.604c0-1.337-.026-3.061-1.866-3.061-1.867 0-2.153 1.459-2.153 2.968v5.697h-3v-10h2.881v1.367h.041c.401-.76 1.379-1.561 2.838-1.561 3.036 0 3.6 1.999 3.6 4.596v5.598z" />
          </svg>
        </a>
      </div>
    </div>
  );
}

export default OwnerInfo;
