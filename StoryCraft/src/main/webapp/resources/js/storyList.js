// 디버깅용 로그: JSP에서 전달된 userId 확인
console.log("User ID:", userId);

// 스토리 상세 모달 열기
function openStoryModal(button) {
    // 스토리 아이템 가져오기
    const storyItem = $(button).closest('.story-item');

    // 데이터 속성에서 정보 가져오기
    const stNum = storyItem.data('stnum');
    const title = storyItem.data('title');
    const cover = storyItem.data('cover');
    const uId = storyItem.data('uid');
    const viewNum = storyItem.data('viewnum');
    const sugNum = storyItem.data('sugnum');
    const crDate = storyItem.data('crdate');
    const genre = storyItem.data('genre');

    // 본인이 작성한 스토리인지 확인
    const isOwner = userId === uId;

    // 모달 내용 설정
    $('#storyModal .modal-title').text(title);
    $('#storyModal .modal-body img').attr('src', contextPath + 'uploads/' + cover);
    $('#storyModal .modal-body .genre').text('장르: ' + genre);
    $('#storyModal .modal-body .uId').text('작성자: ' + uId);
    $('#storyModal .modal-body .viewNum').text('조회수: ' + viewNum);
    $('#storyModal .modal-body .sugNum').text('추천수: ' + sugNum);
    $('#storyModal .modal-body .crDate').text('생성일: ' + new Date(crDate).toLocaleString());

    // 버튼 이벤트 설정
    if (isOwner) {
        $('#storyModal .edit-btn').show().attr('onclick', 'editStory(' + stNum + ')');
        $('#storyModal .delete-btn').show().attr('onclick', 'deleteStory(' + stNum + ')');
        $('#storyModal .report-btn').hide();
    } else {
        $('#storyModal .edit-btn').hide();
        $('#storyModal .delete-btn').hide();
        $('#storyModal .report-btn').show().attr('onclick', 'openReportModal(' + stNum + ')');
    }
    $('#storyModal .recommend-btn').attr('onclick', 'recommendStory(' + stNum + ')');
    $('#storyModal .play-btn').attr('onclick', 'playStory(' + stNum + ')');

    // 추천 버튼 비활성화 조건
    if (isOwner || isAlreadyRecommended(stNum)) {
        $('#storyModal .recommend-btn').prop('disabled', true).text('추천 불가');
    } else {
        $('#storyModal .recommend-btn').prop('disabled', false).text('추천하기');
    }

    // 모달 열기
    $('#storyModal').modal('show');
}

// 이미 추천했는지 확인하는 함수 (추가적인 로직이 필요할 수 있음)
function isAlreadyRecommended(stNum) {
    // 서버에서 현재 사용자가 해당 스토리를 이미 추천했는지 확인하는 API 호출 필요
    // 현재는 클라이언트에서 처리하지 않으므로 항상 false를 반환
    // 실제 구현 시, 추천 상태를 서버에서 받아와 처리해야 함
    return false;
}

// 스토리 수정 페이지로 이동
function editStory(stNum) {
    window.location.href = contextPath + 'story/edit?stNum=' + stNum;
}

// 스토리 삭제
function deleteStory(stNum) {
    if (confirm('정말로 이 스토리를 삭제하시겠습니까?')) {
        $.ajax({
            url: contextPath + 'story/delete',
            method: 'POST',
            data: { stNum: stNum },
            success: function (data) {
                if (data.success) {
                    alert('스토리가 삭제되었습니다.');
                    window.location.reload();
                } else {
                    alert(data.message || '스토리 삭제에 실패했습니다.');
                }
            },
            error: function (error) {
                console.error('스토리 삭제 중 오류 발생:', error);
                alert('스토리 삭제 중 오류가 발생했습니다.');
            }
        });
    }
}

// 신고 모달 열기
function openReportModal(stNum) {
            const reportModal = `
                <div class="modal fade" id="reportModal" tabindex="-1" role="dialog" aria-labelledby="reportModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">스토리 신고</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="닫기">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form id="reportForm" enctype="multipart/form-data">
                                    <input type="hidden" name="stNum" value="${stNum}">
                                    <div class="form-group">
                                        <label for="reTypeCode">신고 유형:</label>
                                        <select id="reTypeCode" name="reTypeCode" class="form-control" required>
                                            <option value="">선택하세요</option>
                                            <option value="CR-01">회원 신고</option>
                                            <option value="CR-02">스토리 신고</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="reText">신고 내용:</label>
                                        <textarea id="reText" name="reText" class="form-control" rows="3" required></textarea>
                                    </div>
                                    <!-- 이미지 첨부 필드 추가 -->
                                    <div class="form-group">
                                        <label for="reportImage">이미지 첨부 (선택 사항):</label>
                                        <input type="file" id="reportImage" name="reportImage" class="form-control">
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
                                <button type="button" class="btn btn-danger" onclick="submitReport()">신고하기</button>
                            </div>
                        </div>
                    </div>
                </div>
            `;
            $('body').append(reportModal);
            $('#reportModal').modal('show');

            // 모달이 닫힐 때 제거
            $('#reportModal').on('hidden.bs.modal', function () {
                $(this).remove();
            });
        }

        // 신고 제출 함수
        function submitReport() {
            const form = $('#reportForm')[0]; // JavaScript DOM 객체로 변환
            const formData = new FormData(form); // FormData 객체 생성

            $.ajax({
                url: contextPath + 'story/report',
                method: 'POST',
                data: formData,
                processData: false, // 파일 전송을 위해 false 설정
                contentType: false, // 파일 전송을 위해 false 설정
                success: function(data) {
                    if (data.success) {
                        alert('신고가 접수되었습니다.');
                        $('#reportModal').modal('hide');
                    } else {
                        alert(data.message || '신고에 실패했습니다.');
                    }
                },
                error: function(error) {
                    console.error('신고 처리 중 오류 발생:', error);
                    alert('신고 처리 중 오류가 발생했습니다.');
                }
            });
        }

// 추천하기
function recommendStory(stNum) {
    $.ajax({
        url: contextPath + 'story/recommend',
        method: 'POST',
        data: { stNum: stNum },
        success: function (data) {
            if (data.success) {
                alert('스토리를 추천했습니다.');
                // 추천 수 갱신
                $('.sugNum').text('추천수: ' + data.recommendCount);
            } else {
                alert(data.message || '추천에 실패했습니다.');
            }
        },
        error: function (error) {
            console.error('스토리 추천 중 오류 발생:', error);
            alert('스토리 추천 중 오류가 발생했습니다.');
        }
    });
}

// 스토리 플레이 페이지로 이동
function playStory(stNum) {
    window.location.href = contextPath + 'story/play?stNum=' + stNum;
}

// 필터링 및 정렬 함수
function filterAndSort() {
    const genre = document.getElementById('categorySelect').value;
    const sort = document.getElementById('sortSelect').value;
    window.location.href = `${contextPath}story/list?genre=${encodeURIComponent(genre)}&sort=${encodeURIComponent(sort)}`;
}
