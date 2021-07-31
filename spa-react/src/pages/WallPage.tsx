import "./../styles/base.scss";
import "./../styles/wall.scss";

interface PostProps {
  name: String;
  body: String;
}

const posts: Array<PostProps> = [
  {
    name: "David Lukens",
    body: "Whether you're coming in fresh to using Typescript with React or are a grizzled veteran looking to add more functional components to your codebase by introducing hooks, it's important to know the different ways to define props",
  },
  {
    name: "David Lukens",
    body: "Accomplishing the same functionality using the non-destructured syntax would look something like this:    ",
  },
  {
    name: "David Lukens",
    body: "You can read more about the benefits of React.FC here",
  },
];

function Post(props: PostProps) {
  return (
    <div className="feed-item">
      <div>{props.name}</div>
      <div>{props.body}</div>
    </div>
  );
}

function PostInput() {
  var input: String | null = null;
  function onInput(textInput: React.FormEvent<HTMLDivElement>) {
    input = textInput.currentTarget.innerText;
  }

  function doPost() {
    const inputCache = input;
    if (inputCache != null) {
      console.log(inputCache);
      document.getElementById("post-input")!!.innerText = "";
    }
    input = null;
  }

  return (
    <div className="feed-item">
      <div id="post-input" role="textbox" contentEditable onInput={onInput} />
      <button id="post-button" onClick={doPost}>
        Post
      </button>
    </div>
  );
}

export default function WallPage() {
  return (
    <div id="feed-page">
      <div>
        <PostInput />
        {posts.map((post) => (
          //todo bind to unique ids
          <Post name={post.name} body={post.body} />
        ))}
      </div>
    </div>
  );
}
