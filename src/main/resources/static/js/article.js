// DELETE API /article/{id}
const deleteButtom = document.getElementById('delete-btn')

if(deleteButtom){
    deleteButtom.addEventListener('click', event => {
        let id = document.getElementById('article-id').value;
        fetch(`/api/articles/${id}`, {
            method: 'DELETE'
            // json
        }).then(() => {
            alert('삭제가 완료되었습니다 :-)');
            location.replace('/articles');  // location.href('') - 브라우저상에 기록이 남고 replace는 기록이 남지 않는다.
        })
    })
}

// id가 modify-btn인 엘리먼트 조회
const modifyButton = document.getElementById('modify-btn');

if (modifyButton) {
    // 클릭 이벤트가 감지되면 수정 API 요청
    modifyButton.addEventListener('click', event => {
        let params = new URLSearchParams(location.search);
        let id = params.get('id');

        fetch(`/api/articles/${id}`, {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        }).then(() => {
            alert('수정이 완료되었습니다');
            location.replace(`/articles/${id}`);
        });
    });
}

// 게시글 생성 기능
const createButton = document.getElementById('create-btn');
if (createButton) {
    createButton.addEventListener('click', event => {
        fetch(`/api/articles`, {
            method: 'POST',
            headers: {
                "Content-Type": "application/json"
            },
            body : JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            }),
        }).then(() => {
            alert('등록 완료되었습니다 :-)');
            location.replace("/articles");
        })
    })
}